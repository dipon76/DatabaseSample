package com.example.dipon.databasesample;

import android.provider.BaseColumns;

/**
 * Created by Dipon on 5/4/2017.
 */

public final class DatabaseContract {

    private DatabaseContract () {}

    public static class GroupInfo implements BaseColumns {
        public static final String TABLE_NAME = "GroupInfo" ;
        public static final String COLUMN_GROUP_NAME = "GroupName";
        public static final String COLUMN_GROUP_ADMIN = "GroupAdminNumber" ;
        public static final String COLUMN_PARTICIPANTS_NO = "GroupParticipantsNo" ;
    }

    public static class ParticipantsInfo implements BaseColumns {
        public static final String TABLE_NAME = "ParticipantsInfo";
        public static final String COLUMN_PARTICIPANT_NAME = "ParticipantName";
        public static final String COLUMN_GROUP_NO = "GroupID" ;
        public static final String COLUMN_PARTICIPANTS_NUMBER = "ParticipantNumber" ;
    }
}
