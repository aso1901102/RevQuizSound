package jp.ac.asojuku.revquizsound

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //SoundPool型のインスタンス変数のフィールドプロパティを宣言
    private lateinit var soundPool: SoundPool;
    //効果音の音源(Sound)のリソースID
    //仮の初期値で初期化
    private  var answer = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transforquestion.setOnClickListener {
            //ボタンがクリックされた時の処理
            //画面遷移用のインテントを生成
            val intent = Intent(this,QuestActivity::class.java)
            //intentをもとに画面遷移を実行
            this.startActivity(intent)
        }

        answerButton.setOnClickListener {
            soundPool.play(answer,1.0f,1.0f,0,0,1.0f);
        }
    }

    override fun onResume() {
        super.onResume()
        //画面が表示されている間だけインスタンスをメモリに保持する
        //SoundPoolクラスのインスタンスを変数に代入
        this.soundPool =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                //4.4 LOLLIPOPより古い端末(非推奨のメソッドを利用）
                SoundPool(     //SoundPoolクラスのコンストラクタ
                    2,                  //同時にならせる音源の数
                    AudioManager.STREAM_ALARM,  //オーディオの種類
                    0                 //音源の音質。
                );
            } else {
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM).build();
                //オーディオ設定を使ってSoundPoolのインスタンスを作る
                SoundPool.Builder().setMaxStreams(1)                //同時音源数
                    .setAudioAttributes(audioAttributes).build();   //オーディオ設定を登録してビルド
            }
        this.answer = soundPool.load(this,R.raw.answer,1)
    }



}
