package pl.mkikolski.mojacodziennatabletka.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import java.io.File


@Composable
fun CameraScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasCameraPermission by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCameraPermission = granted
        }
    )

    LaunchedEffect(Unit) {
        hasCameraPermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!hasCameraPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (hasCameraPermission) {
        // Camera functionality
        val cameraProvider = rememberCameraProvider(context)
        val imageCapture = remember { ImageCapture.Builder().build() }

        Box(modifier = Modifier.fillMaxSize()) {
            CameraPreview(cameraProvider, lifecycleOwner, imageCapture)
            CaptureButton(
                onClick = {
                    capturePhoto(context, imageCapture)
                },
                modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
            )
        }
    } else {
        // Inform the user that permission is required
        PermissionDeniedMessage()
    }
}

@Composable
fun CameraPreview(
    cameraProvider: ProcessCameraProvider?,
    lifecycleOwner: LifecycleOwner,
    imageCapture: ImageCapture
) {
    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                setupCamera(cameraProvider, lifecycleOwner, imageCapture, this)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun setupCamera(
    cameraProvider: ProcessCameraProvider?,
    lifecycleOwner: LifecycleOwner,
    imageCapture: ImageCapture,
    previewView: PreviewView
) {
    val preview = Preview.Builder().build().also {
        it.setSurfaceProvider(previewView.surfaceProvider)
    }
    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    cameraProvider?.unbindAll()
    cameraProvider?.bindToLifecycle(
        lifecycleOwner, cameraSelector, preview, imageCapture
    )
}

@Composable
fun CaptureButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = modifier) {
        Text(text = "Capture")
    }
}

fun capturePhoto(context: Context, imageCapture: ImageCapture?) {
    val outputFileOptions = ImageCapture.OutputFileOptions.Builder(
        File(context.externalMediaDirs.firstOrNull(), "${System.currentTimeMillis()}.jpg")
    ).build()

    imageCapture?.takePicture(
        outputFileOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Toast.makeText(context, "Photo captured: ${outputFileResults.savedUri}", Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: ImageCaptureException) {
                Toast.makeText(context, "Capture failed: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        }
    )
}

@Composable
fun rememberCameraProvider(context: Context): ProcessCameraProvider? {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    return try {
        cameraProviderFuture.get()
    } catch (e: Exception) {
        null
    }
}

@Composable
fun PermissionDeniedMessage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Camera permission is required to use this feature.")
    }
}