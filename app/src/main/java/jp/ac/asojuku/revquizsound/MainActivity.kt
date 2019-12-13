package jp.ac.asojuku.revquizsound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transforquestion.setOnClickListener {
            //ボタンがクリックされた時の処理
            //画面遷移用の院展とを生成
            val intent = Intent(this,QuestActivity::class.java)
            //intentをもとに画面遷移を実行
            this.startActivity(intent)
        }
    }


}
