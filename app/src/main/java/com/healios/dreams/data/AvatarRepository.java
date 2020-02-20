package com.healios.dreams.data;

import com.healios.dreams.R;

import java.util.ArrayList;
import java.util.List;

public class AvatarRepository {

    private static AvatarRepository instance;

    private List<Integer> mAvatarList;

    public static AvatarRepository getInstance() {
        if (instance == null) {
            instance = new AvatarRepository();
        }
        return instance;
    }


    public List<Integer> getAvatarList() {
        return this.mAvatarList;
    }

    private void createAvatarList(){
        List<Integer> avatarList = new ArrayList<>();
        avatarList.add(R.drawable.selectavatar_f_1);
        avatarList.add(R.drawable.selectavatar_f_2);
        avatarList.add(R.drawable.selectavatar_f_3);
        avatarList.add(R.drawable.selectavatar_f_4);
        avatarList.add(R.drawable.selectavatar_f_5);
        avatarList.add(R.drawable.selectavatar_f_6);

        avatarList.add(R.drawable.selectavatar_m_1);
        avatarList.add(R.drawable.selectavatar_m_2);
        avatarList.add(R.drawable.selectavatar_m_3);
        avatarList.add(R.drawable.selectavatar_m_4);
        //avatarList.add(R.drawable.selectavatar_m_5);
        //avatarList.add(R.drawable.selectavatar_m_6);

        this.mAvatarList = avatarList;
    }



}
