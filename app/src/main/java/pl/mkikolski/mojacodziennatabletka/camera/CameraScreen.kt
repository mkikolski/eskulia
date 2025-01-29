package pl.mkikolski.mojacodziennatabletka.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import java.io.File
import java.util.concurrent.TimeUnit


@Composable
fun CameraScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var hasCameraPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted -> hasCameraPermission = granted }
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
        val cameraProvider = produceState<ProcessCameraProvider?>(null) {
            val provider = ProcessCameraProvider.getInstance(context).get()
            value = provider
        }.value

        val imageCapture = remember { ImageCapture.Builder().build() }
        val imageAnalyzer = remember {
            ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
        }
        val barcodeAnalyzer = remember { BarcodeAnalyzer(context) }

        LaunchedEffect(imageAnalyzer) {
            imageAnalyzer.setAnalyzer(
                ContextCompat.getMainExecutor(context),
                barcodeAnalyzer
            )
        }

        if (cameraProvider != null) {
            Box(modifier = Modifier.fillMaxSize()) {
                CameraPreview(cameraProvider, lifecycleOwner, imageCapture, imageAnalyzer)
                ScanFrameOverlay()
                CaptureButton(
                    onClick = { capturePhoto(context, imageCapture) },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }
        }
    } else {
        PermissionDeniedMessage()
    }
}

@Composable
fun CameraPreview(
    cameraProvider: ProcessCameraProvider,
    lifecycleOwner: LifecycleOwner,
    imageCapture: ImageCapture,
    imageAnalyzer: ImageAnalysis
) {
    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                post {
                    setupCamera(cameraProvider, lifecycleOwner, imageCapture, imageAnalyzer, this)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}

fun setupCamera(
    cameraProvider: ProcessCameraProvider,
    lifecycleOwner: LifecycleOwner,
    imageCapture: ImageCapture,
    imageAnalyzer: ImageAnalysis,
    previewView: PreviewView
) {
    val preview = Preview.Builder().build().also {
        it.setSurfaceProvider(previewView.surfaceProvider)
    }
    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    cameraProvider.unbindAll()

    imageAnalyzer.setAnalyzer(
        ContextCompat.getMainExecutor(previewView.context),
        BarcodeAnalyzer(previewView.context)
    )

    cameraProvider.bindToLifecycle(
        lifecycleOwner, cameraSelector, preview, imageCapture, imageAnalyzer
    )
}

@Composable
fun CaptureButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.LightGray.copy(alpha = 0.3f))
        )
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
    var cameraProvider by remember { mutableStateOf<ProcessCameraProvider?>(null) }

    LaunchedEffect(cameraProviderFuture) {
        try {
            cameraProvider = cameraProviderFuture.get(3, TimeUnit.SECONDS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    return cameraProvider
}

@Composable
fun PermissionDeniedMessage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Camera permission is required to use this feature.")
    }
}

@Composable
fun ScanFrameOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .border(4.dp, Color.White, RoundedCornerShape(12.dp))
                .background(Color.Transparent)
        )
    }
}