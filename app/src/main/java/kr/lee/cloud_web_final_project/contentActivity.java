package kr.lee.cloud_web_final_project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class contentActivity extends AppCompatActivity {//이 엑티비티 클래스는 리스트에서 클릭한 글의 제목,본문,사진을 출력하기 위한 클래스입니다.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        TextView title = (TextView)findViewById(R.id.content_title);
        ImageView image = (ImageView)findViewById(R.id.content_image);
        TextView txt = (TextView)findViewById(R.id.content_content);
        TextView hash_tags = (TextView)findViewById(R.id.content_tags);
        Button button = (Button)findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//뒤로가기 버튼을 누르면 엑티비티가 종료됩니다.
                finish();
            }
        });

        Intent intent = getIntent();//각 항목들을 출력합니다.
        title.setText("제목:"+intent.getStringExtra("title").toString());
        image.setImageResource(intent.getIntExtra("image", 0));
        txt.setText(intent.getStringExtra("content_text".toString()));
        hash_tags.setText("#"+intent.getStringExtra("tags".toString()));



    }
}