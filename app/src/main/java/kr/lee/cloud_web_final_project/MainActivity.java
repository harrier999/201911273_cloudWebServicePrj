package kr.lee.cloud_web_final_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    ListView list;
    int count = 4;
    ArrayList<String> titles=new ArrayList<String>();

    Integer[] images ={
            R.drawable.gong6,
            R.drawable.gong2,
            R.drawable.gong3,
            R.drawable.gong5,
            R.drawable.gong10,
            R.drawable.gong7,
            R.drawable.gong8,
            R.drawable.gong9
    };
    ArrayList<String>content_text = new ArrayList<String>();

    ArrayList<String> tags = new ArrayList<String>();
    /*정보를 저장할 변수들 선언*/

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//레이아웃 설정
        tags.add("공연장");
        tags.add("공연장");
        tags.add("공연장");
        tags.add("공연장");
        tags.add("공연장");
        tags.add("공연장");
        tags.add("공연장");
        tags.add("공연장");

        titles.add("코엑스 아트홀");
        titles.add("신촌 문화발전소");
        titles.add("서울광장");
        titles.add("프로젝트박스 시야");
        titles.add("정효 아트센터");
        titles.add("구로 꿈나무극장");
        titles.add("용산 더줌아트센터");
        titles.add("용산 일산홀");

        content_text.add("서울시 구로구 구로동 3-39");
        content_text.add("서울시 구로구 구로동 3-39");
        content_text.add("서울특별시 중구 태평로2가 17-3\n\n02-120\n\nhttps://cultureseoul.co.kr/");
        content_text.add("서울시 구로구 구로동 3-39");
        content_text.add("서울시 구로구 구로동 3-39");
        content_text.add("맛있어요");
        content_text.add("맛있어요");
        content_text.add("맛있어요");//각 변수들에 정보 저장합니다.
        Button b = (Button)findViewById(R.id.button01);
        text= (TextView)findViewById(R.id.tt);
        b.setOnClickListener(new View.OnClickListener() {//글쓰기 버튼을 누르면 Activity2가 실행된다
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });


        Spinner spinner = (Spinner) findViewById(R.id.spinner);//스피너를 이용해 해쉬태그를 설정할 수 있습니다.
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this, R.array.hash_tags_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if(0<=pos) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                    intent.putExtra("GENRE", pos);
                    startActivity(intent);

                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });


        CustomList adapter = new CustomList(MainActivity.this);//글 리스트들을 보여주기 위한 아답터입니다.
        list = (ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(),titles[+position],Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, contentActivity.class);
                intent.putExtra("image",images[position]);
                intent.putExtra("title",titles.get(position));
                intent.putExtra("content_text",content_text.get(position));
                intent.putExtra("tags",tags.get(position));//이미지, 타이틀, 본문, 해쉬태그를 리스트 새로운 엑티비티에 전달합니다.
                //새 엑티비티에서는 정보 글을 출력합니다.
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {//글쓰기를 완료하면 글 정보를 받아옵니다.
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){


                titles.add(data.getStringExtra("INPUT_TITLE"));
                content_text.add(data.getStringExtra("INPUT_TEXT"));
                tags.add(data.getStringExtra("TAG"));//제목, 본문, 해쉬태그를 받아옵니다.

            }
        }
    }

    public class CustomList extends ArrayAdapter<String> {//리스트를 생성합니다.
        private final Activity context;
        public CustomList(Activity context){
            super(context, R.layout.listitem, titles);
            this.context = context;
        }


        @Override
        public View getView(int position, View view, ViewGroup parent) {//각 리스트에 보여질 정보를 매핑합니다.
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView =inflater.inflate(R.layout.listitem,null,true);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rating = (TextView) rowView.findViewById(R.id.rating);
            TextView hash = (TextView) rowView.findViewById(R.id.tags);

            title.setText(titles.get(position));
            imageView.setImageResource(images[position]);
            rating.setText("9.0"+position);
            hash.setText("#"+tags.get(position));
            return rowView;


        }
    }
}