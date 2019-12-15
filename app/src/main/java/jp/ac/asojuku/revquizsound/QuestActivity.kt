package jp.ac.asojuku.revquizsound

import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quest.*

class QuestActivity : AppCompatActivity() {
    //SoundPool型のインスタンス変数のフィールドプロパティを宣言
    private lateinit var soundPool: SoundPool;
    //効果音の音源(Sound)のリソースID
    //仮の初期値で初期化
    private  var uncorrect = 0;
    private  var correct = 0;
    private  var clap = 0;
    private  var question = 0;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)
        transforanswer.setOnClickListener {
            //ボタンがクリックされた時の処理
            //画面遷移用のインテントを生成
            val intent = Intent(this,MainActivity::class.java)
            //intentをもとに画面遷移を実行
            this.startActivity(intent)
        }

        buttonFalse.setOnClickListener {
            soundPool.play(uncorrect,1.0f,1.0f,0,0,1.0f);
        }

        buttonTrue.setOnClickListener {
            soundPool.play(correct,1.0f,1.0f,0,0,1.0f);
        }

        buttonClap.setOnClickListener {
            soundPool.play(clap,1.0f,1.0f,0,0,1.0f);
        }

        buttonQuestion.setOnClickListener {
            soundPool.play(question,1.0f,1.0f,0,0,1.0f);
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
        this.uncorrect = soundPool.load(this,R.raw.uncurrect,1)
        this.correct = soundPool.load(this,R.raw.currect,1)
        this.clap = soundPool.load(this,R.raw.clappinghands,1)
        this.question = soundPool.load(this,R.raw.powerup10,1)
    }
}
