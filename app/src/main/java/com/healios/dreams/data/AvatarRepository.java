package com.healios.dreams.data;

import com.healios.dreams.R;
import com.healios.dreams.model.AvatarModel;

import java.util.ArrayList;
import java.util.List;

public class AvatarRepository {

    private static AvatarRepository instance;

    private List<AvatarModel> mAvatarList;

    public static AvatarRepository getInstance() {
        if (instance == null) {
            instance = new AvatarRepository();
        }

        return instance;
    }

    public List<AvatarModel> getAvatarList() {
        if (mAvatarList == null)
            createAvatarList();

        return this.mAvatarList;
    }

    private void createAvatarList() {

        List<AvatarModel> avatarArray = new ArrayList<>();
        avatarArray.add(new AvatarModel(1, R.drawable.selectavatar_m_1, R.drawable.user_m_1));
        avatarArray.add(new AvatarModel(2, R.drawable.selectavatar_m_2, R.drawable.user_m_2));
        avatarArray.add(new AvatarModel(3, R.drawable.selectavatar_m_3, R.drawable.user_m_3));
        //avatarArray.add(new AvatarModel(4, R.drawable.selectavatar_m_4, R.drawable.user_m_4));
        //FIXME: Uncomment when resource is available
        //avatarArray.add(new AvatarModel(5, R.drawable.selectavatar_m_5));
        //avatarArray.add(new AvatarModel(6, R.drawable.selectavatar_m_6));
        avatarArray.add(new AvatarModel(7, R.drawable.selectavatar_f_1, R.drawable.user_f_1));
        avatarArray.add(new AvatarModel(8, R.drawable.selectavatar_f_2, R.drawable.user_f_2));
        avatarArray.add(new AvatarModel(9, R.drawable.selectavatar_f_3, R.drawable.user_f_3));
        avatarArray.add(new AvatarModel(10, R.drawable.selectavatar_f_4, R.drawable.user_f_4));
        avatarArray.add(new AvatarModel(11, R.drawable.selectavatar_f_5, R.drawable.user_f_5));
        avatarArray.add(new AvatarModel(12, R.drawable.selectavatar_f_6, R.drawable.user_f_6));

        this.mAvatarList = avatarArray;
    }

}
