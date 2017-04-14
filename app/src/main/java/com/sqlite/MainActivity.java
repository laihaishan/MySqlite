package com.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sqlite.db.User;
import com.sqlite.db.UserService;

import java.util.List;

public class MainActivity extends Activity {
    private Button add, query, delete;
    UserService userService =null;//管理类
    List<User> users = null;
    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userService=new UserService(this);  //使用碎片必须放在onCreate
        add = (Button) findViewById(R.id.add);
        query = (Button) findViewById(R.id.query);
        delete = (Button) findViewById(R.id.delete);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setName("张三");
                user.setAge("18");
                boolean flag = userService.add(user);
                Log.v("==添加成功==", "==共有==" + userService.query().size());
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = userService.delete(user.getName());
                Log.v("==删除成功==", b + "==共有==" + userService.query().size());
            }
        });
    }

    private void query() {

        users = userService.query();
        if (users.size() > 0) {
            for (int i = 0; i < users.size(); i++) {
                Log.v("======", users.get(i).getName() + "====" + users.get(i).getAge());
            }
        } else {
            Log.v("===", "==没有数据==");

        }
    }

}
