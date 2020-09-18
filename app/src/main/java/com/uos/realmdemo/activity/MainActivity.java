package com.uos.realmdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.uos.realmdemo.R;
import com.uos.realmdemo.bean.User;

import java.util.Arrays;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 说明：
         * 无主键：
         *      使用copyToRealmOrUpdate(obj)会报错
         *
         * 有主键：
         *      1.主键类型必须为String或者整形(byte、short、int、long)或者他们的包装类型
         *      2.不能使用createObject(User.class)，应该使用createObject(User.class, Object primaryKey)
         *      3.使用copyToRealm(obj)会冲突崩溃
         *      4.使用使用copyToRealmOrUpdate(obj)，数据库有该主键则执行更新，无则新增
         */

        // 1.无主键，存储数据的两种方式
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        User user = realm.createObject(User.class);
//        user.setName("xxc");
//        user.setAge(18);
//        realm.commitTransaction();

//        Realm realm = Realm.getDefaultInstance();
//        User user = realm.createObject(User.class);
//        user.setName("xxc");
//        user.setAge(18);
//        realm.beginTransaction();
//        realm.copyToRealm(user);
//        realm.commitTransaction();

        // 创建数据
        final User user = new User();
        user.setName("xxc");
        user.setAge(18);

        // 获取Realm实例
        Realm realm = Realm.getDefaultInstance();

        // 查询 此时没有数据
//        final RealmResults<User> results = realm.where(User.class).findAll();
//        Log.d(TAG, results.size() + "===");

        // 持久化数据 由自己维护主键自增
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentMaxId = realm.where(User.class).max("id");
                int nextId;
                if (currentMaxId == null) {
                    nextId = 0;
                } else {
                    nextId = currentMaxId.intValue() + 1;
                }
                user.setId(nextId);
                realm.insertOrUpdate(user);
            }
        });
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(user);
//        realm.insert(user);
//        realm.commitTransaction();

        // 监听数据变化
//        results.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<User>>() {
//            @Override
//            public void onChange(RealmResults<User> users, OrderedCollectionChangeSet changeSet) {
//                int[] insertions = changeSet.getInsertions();
//                Log.d(TAG, "insertions ==> " + Arrays.toString(insertions));
//            }
//        });

        // 更新数据
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm bgRealm) {
//                RealmResults<User> users = bgRealm.where(User.class).equalTo("age", 18).findAll();
//                if (users != null && users.size() > 0) {
//                    users.get(0).setName("cxx");
//                }
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//            }
//        });

        final RealmResults<User> realmResults = realm.where(User.class).findAll();
        for (int i = 0; i < realmResults.size(); i++) {
            Log.d(TAG, realmResults.get(i).getName());
        }
        realm.close();
    }
}