```java
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 101;
    private FrameLayout container;
    private CameraRenderer renderer;
    private TextureView textureView;
    private int filterId = R.id.filter0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(container = new FrameLayout(this));
        setTitle("Original");

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Camera access is required.", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                        REQUEST_CAMERA_PERMISSION);
            }

        } else {
            setupCameraPreviewView(); // 权限申请之后的操作
        }
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setupCameraPreviewView(); // 权限申请之后的操作
                }
            }
        }
    }
```

### shouldShowRequestPermissionRationale
在允许和不再提示后，为false;  
在第一次打开程序和拒绝后为true。  
