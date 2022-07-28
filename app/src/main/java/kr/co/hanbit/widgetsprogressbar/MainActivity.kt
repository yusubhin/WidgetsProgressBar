package kr.co.hanbit.widgetsprogressbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kr.co.hanbit.widgetsprogressbar.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /* 앱이 실행되고 3초 후에 showProgress(false)를 호출하기 */
        //Thread.sleep(3000) : 메인스레드에서 동작을 멈추는 Thread.sleep() 메서드를 호출하므로 에뮬레이터 화면에 아무것도 보이지 않음
        //1차 수정본 : 3초간 프로그래스바가 동작하다가 앱이 다운됨(UI와 관련된 모든 코드는 메인 스레드에서 실행해야만 함 - showProgress 메서드를 백그라운드에서 호출하기 때문에 생기는 오류)
        thread(start=true) { //함수 블록 안의 코드가 모두 백그라운드(서브 스레드)에서 동작
            Thread.sleep(3000)
            //showProgress(false)
            //2차 수정본
            runOnUiThread { //메인 스레드
                showProgress(false)
            }
        }
    }

    fun showProgress(show: Boolean) { //리니어 레이아웃을 숨겼다 보였다 할 수 있는 코드
        if (show) {
            binding.progressLayout.visibility = View.VISIBLE
        } else {
            binding.progressLayout.visibility = View.GONE
        }
        //binging.progressLayout.visibility = if(show) View.VISIBLE else View.GONE로 축약 가능
    }
}