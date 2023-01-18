package com.example.colorrecg;
//gerekli kütüphaneler eklendi
import static android.content.ContentValues.TAG;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity implements OnTouchListener, CvCameraViewListener2 {
    //değişken isimleri belirlendi, renk tanımlama, sesli olarak okumak için değişkenler tanımlandı
    private CameraBridgeViewBase mCamera;
    private Mat mRgba, imgColor;
    private Scalar mColorHsv;
    private Scalar mColorRgba;
    private ColorUtil colorUtil;

    TextToSpeech textToSpeech;
    TextView text_coordinates;
    TextView text_color;

    double x = -1;
    double y = -1;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    mCamera.enableView();
                    mCamera.setOnTouchListener(MainActivity.this);
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //tasarımdaki tanımlamalar ile kod eşleştirildi
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        text_coordinates = findViewById(R.id.text_coordinates);
        text_color = findViewById(R.id.text_color);
        //ColorUtil class'ından colorUtil nesnesi yaratıldı
        colorUtil = new ColorUtil();
        AtomicBoolean textToSpeechIsInitialized = new AtomicBoolean(false);
        //konuşmada hata olup olmadığına bakarak hata varsa loglayarak gösterir
        //eğer hata yoksa result değerinde konuşma dilini ingilizce ayarlar, eğer result'da dil
        // datası eksikse veya dil desteklenmiyorsa hata mesajı loglanır
        textToSpeech = new TextToSpeech(getApplicationContext(), i -> {
            if (i != TextToSpeech.ERROR) {
                textToSpeechIsInitialized.set(true);
                int result = textToSpeech.setLanguage(Locale.ENGLISH);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.d(TAG, "This Language is not supported");
                }
            } else {
                Log.d(TAG, "Initilization Failed!");
            }


        });
        //tasarımdaki java kamera görünümünü mCamera değişkenine atanır
        mCamera = findViewById(R.id.java_camera_view);
        mCamera.setVisibility(SurfaceView.VISIBLE);
        mCamera.setCvCameraViewListener(this);
    }

    @Override
    public void onPause() {
        //durdurulunca kamerayı ve sesi kapatma kodu
        super.onPause();
        if (mCamera != null) mCamera.disableView();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        //konuşmanın başarılı olup olmamasına göre loglama veya dilin set edilmesini döndürür
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(Locale.getDefault());
        } else {
            Log.e("TTS", "Initialization failed");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0, this, mLoaderCallback);
        } else {
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void onDestroy() {
        //kamerayı yok etmek için
        super.onDestroy();
        if (mCamera != null) mCamera.disableView();
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        //kamera başlatıldığında rengin ayarlanması için değişkenler belirlendi
        mRgba = new Mat();
        mColorRgba = new Scalar(255);
        mColorHsv = new Scalar(255);
        imgColor = new Mat(height, width, CvType.CV_8UC1);

    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        Imgproc.cvtColor(mRgba, imgColor, Imgproc.COLOR_RGBA2BGRA);
        return imgColor;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //dokunulduğunda nereye dokunulduğunu gösteren kodlar
        //sütun ve satırları alarak en düşük ve en yüksek değerler belirlenir
        //bir takım hesaplamalar ile dokunulan bölgenin x ve y koordinatları alınır
        int cols = mRgba.cols();
        int rows = mRgba.rows();

        double yLow = (double) mCamera.getHeight() * 0.1;
        double yHigh = (double) mCamera.getHeight() * 0.940;
        double xScale = (double) cols / (double) mCamera.getWidth();
        double yScale = (double) rows / (yHigh - yLow);

        Log.i(TAG, "touch image coordinatesss : " + x + y);

        int xOffset = (mCamera.getWidth() - cols) / 2;
        int yOffset = (mCamera.getHeight() - rows) / 2;

        int x = (int) event.getX() - xOffset;
        int y = (int) event.getY() - yOffset;

        if ((x < 0) || (y < 0) || (x > cols) || (y > rows)) return false;

        text_coordinates.setText("X:" + Double.valueOf(x) + ", Y :" + Double.valueOf(y));

        Rect touchedRect = new Rect();

        touchedRect.x = (x > 4) ? x - 4 : 0;
        touchedRect.y = (y > 4) ? y - 4 : 0;

        touchedRect.width = (x + 4 < cols) ? x + 4 - touchedRect.x : cols - touchedRect.x;
        touchedRect.height = (y + 4 < rows) ? y + 4 - touchedRect.y : rows - touchedRect.y;

        Mat touchedRegionRgba = mRgba.submat(touchedRect);
        Mat touchedRegionHsv = new Mat();

        //doknulan yerlerin rgba değerini alarak hsv'ye dönüştürür
        //ilk değişken dokunulan bölgenin Rgba değerini alır
        //ikinci değişken atanacağı değer
        //üçüncü değer rgbli olan bölgeyi hsvli bölgeye dönüştürür
        Imgproc.cvtColor(touchedRegionRgba, touchedRegionHsv, Imgproc.COLOR_RGB2HSV_FULL);

        mColorHsv = Core.sumElems(touchedRegionHsv);
        int pointCount = touchedRect.width * touchedRect.height;
        for (int i = 0; i < mColorHsv.val.length; i++)
            mColorHsv.val[i] /= pointCount;

        mColorRgba = convertScalarHsv2Rgba(mColorHsv);

        //Rengin adı color_name nesnesine atanır, her bir [] içindeki bölge rgba değerlerini temsil eder
        String color_name = colorUtil.getNameFromRgb((int) mColorRgba.val[0], (int) mColorRgba.val[1], (int) mColorRgba.val[2]);

        //rengin hex değeri görüntüdeki yazıya aktarılır
        text_color.setText("Color : #" + String.format("%02X", (int) mColorRgba.val[0]) + String.format("%02X", (int) mColorRgba.val[1]) + String.format("%02X", (int) mColorRgba.val[2]));
        //rengin değeri yazının rengi olarak ayarlanır
        text_color.setTextColor(Color.rgb((int) mColorRgba.val[0], (int) mColorRgba.val[1], (int) mColorRgba.val[2]));
        //söylenecek rengin adı yazdırılan renkten alınır
        //sesli olarak okutma işlemi gerçekleştirilir
        String toSpeak = text_color.getText().toString();
        Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

        Toast.makeText(getApplicationContext(), color_name, Toast.LENGTH_SHORT).show();
        textToSpeech.speak(color_name, TextToSpeech.QUEUE_FLUSH, null);
        return false;
    }

    private Scalar convertScalarHsv2Rgba(Scalar hsvColor) {
        Mat pointMatRgba = new Mat();
        Mat pointMatHsv = new Mat(1, 1, CvType.CV_8UC3, hsvColor);
        Imgproc.cvtColor(pointMatHsv, pointMatRgba, Imgproc.COLOR_RGB2BGR, 4);
        return new Scalar(pointMatRgba.get(0, 0));
    }
}