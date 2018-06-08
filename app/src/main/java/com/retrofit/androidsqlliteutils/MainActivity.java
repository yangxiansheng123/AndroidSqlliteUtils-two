package com.retrofit.androidsqlliteutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.retrofit.androidsqlliteutils.bean.Student;
import com.retrofit.androidsqlliteutils.db.BaseDao;
import com.retrofit.androidsqlliteutils.db.BaseDaoFactory;
import com.retrofit.androidsqlliteutils.db.BaseQuery;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView vtInsertData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.vt_insertData) {

            BaseQuery baseDao = BaseDaoFactory.getOurInstance().getBaseDao(BaseQuery.class,Student.class);
            baseDao.insert(new Student(2,"18", "男", "1.8米"));
        }else  if (view.getId() == R.id.vt_updateData) {

            BaseQuery baseDao= BaseDaoFactory.getOurInstance().getBaseDao(BaseQuery.class,Student.class);
            //update tb_user where name='jett' set password='1111'
            Student student=new Student("12", "男", "1.6米");
            Student studentW=new Student();
            studentW.setId(2);
            baseDao.update(student,studentW);
        }else  if (view.getId() == R.id.vt_deleteData) {

            BaseQuery baseDao= BaseDaoFactory.getOurInstance().getBaseDao(BaseQuery.class,Student.class);
            Student where=new Student();
            where.setSex("男");
            where.setId(3);
            baseDao.delete(where);
        }else  if (view.getId() == R.id.vt_selectData) {

            BaseQuery baseDao= BaseDaoFactory.getOurInstance().getBaseDao(BaseQuery.class,Student.class);
            Student where=new Student();
            where.setSex("男");
            List<Student> list=baseDao.query(where);
            Log.e("1--------->","listsize="+list.size());
            for(int i=0;i<list.size();i++){
                Log.e("2--------->","list.get(i)="+list.get(i));
            }
        }
    }

    private void initView() {
        vtInsertData = (TextView) findViewById(R.id.vt_insertData);
        vtInsertData.setOnClickListener(MainActivity.this);
        Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show();
    }
}
