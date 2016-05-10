package cn.hophin.shfy.developerstraining;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferencesActivity extends AppCompatActivity {
    private TextView text_username;
    private TextView text_pref_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        text_username = (TextView) findViewById(R.id.text_username);
        text_pref_info= (TextView) findViewById(R.id.text_pref_info);

        //获取应用SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(
                getResources().getString(R.string.PREF_USER_INFO_KEY)
                , MODE_PRIVATE);
        //从SharedPreferences中获取用户信息，初始化文本显示
        text_username.setText(
                sharedPreferences.getString(
                        getResources().getString(R.string.PREF_USER_INFO_USERNAME),
                        getResources().getString(R.string.PREF_USER_INFO_USERNAME_DEFAULT_VALUE)
                )
        );
        //初始化文本显示
        text_pref_info.setText(getPreferences(MODE_PRIVATE).getAll().toString());
    }

    /**
     * Called when the user clicks the addMessage button
     */
    public void addMessage(View view) {
        EditText key= (EditText) findViewById(R.id.edit_key);
        EditText value= (EditText) findViewById(R.id.edit_value);
        //getPreferences(int mode)获取的对象只适用于本Activity
        //getSharedPreferences(String key,int mode)获取的对象适用于Application
        //获取SharedPreferences对象
        SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //添加信息
        editor.putString(
                key.getText().toString(),
                value.getText().toString()
        );
        //提交
        editor.commit();
        //重置输入框
        key.setText("");
        value.setText("");
        //更新文本显示
        text_pref_info.setText(sharedPreferences.getAll().toString());
    }
    /**
     * Called when the user clicks the clearAllMessages button
     */
    public void clearAllMessages(View view) {
        //获取SharedPreferences对象
        SharedPreferences sharedPreferences=getPreferences(MODE_PRIVATE);
        //获取Editor对象
        SharedPreferences.Editor editor=sharedPreferences.edit();
        //清空SharedPreferences
        editor.clear();
        //提交更改
        editor.commit();
        //更新文本显示
        text_pref_info.setText(sharedPreferences.getAll().toString());
    }


}
