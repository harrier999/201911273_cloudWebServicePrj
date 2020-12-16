package kr.lee.cloud_web_final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {//이 엑티비티는 글쓰기를 위한 엑티비티입니다.

    EditText title;
    EditText txt;
    Spinner selector;
    String selected;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_content);
        title = (EditText) findViewById(R.id.title);
        txt = (EditText)findViewById(R.id.write);
        selector = (Spinner)findViewById(R.id.select_tag);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(//해쉬태그를 선택할 스피너입니다.
                this, R.array.hash_tags_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selector.setAdapter(adapter2);

        Button a = (Button)findViewById(R.id.confirm_wrtting);
        Button b = (Button)findViewById(R.id.cancle_writting);
        selector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//카테고리를 선택하면 해당 카테고리를 저장합니다.
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        a.setOnClickListener(new View.OnClickListener() {//입력을 완료하고 완료 버튼을 누르면 메인 엑티비티로 정보를 전송합니다.
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("INPUT_TEXT", txt.getText().toString());
                intent.putExtra("INPUT_TITLE", title.getText().toString());
                intent.putExtra("TAG", selected);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {//취소버튼이 눌리면 엑티비티를 종료합니다.
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}