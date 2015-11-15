//package team.house.cn.HuoseApp.Dao;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteStatement;
//
//import de.greenrobot.dao.AbstractDao;
//import de.greenrobot.dao.AbstractDaoSession;
//import de.greenrobot.dao.Property;
//import de.greenrobot.dao.internal.DaoConfig;
//
///**
// * Created by kn on 15/11/13.
// */
//public class UserDao extends AbstractDao<Users, Long> {
//    public UserDao(DaoConfig config) {
//        super(config);
//    }
//
//    public UserDao(DaoConfig config, AbstractDaoSession daoSession) {
//        super(config, daoSession);
//    }
//    public static class Properties {
//        public final static Property uid = new Property(0, Long.class, "uid", true, "uid");
//        public final static Property username = new Property(1, String.class, "username", false, "username");
//        public final static Property truename = new Property(2, String.class, "truename", false, "truename");
//        public final static Property sex = new Property(3, String.class, "sex", false, "sex");
//        public final static Property marry = new Property(4, String.class, "marry", false, "marry");
//        public final static Property mobile = new Property(5, String.class, "mobile", false, "mobile");
//        public final static Property birthday = new Property(6, String.class, "birthday", false, "birthday");
//        public final static Property province = new Property(7, String.class, "province", false, "province");
//        public final static Property province_nm = new Property(8, String.class, "province_nm", false, "province_nm");
//        public final static Property city = new Property(9, String.class, "city", false, "city");
//        public final static Property city_nm = new Property(10, String.class, "city_nm", false, "city_nm");
//        public final static Property area = new Property(11, String.class, "area", false, "area");
//        public final static Property area_nm = new Property(11, String.class, "area_nm", false, "area_nm");
//        public final static Property email = new Property(11, String.class, "email", false, "email");
//        public final static Property balance = new Property(11, String.class, "balance", false, "balance");
//        public final static Property user_pic = new Property(11, String.class, "user_pic", false, "user_pic");
//        public final static Property is_perfec = new Property(11, String.class, "is_perfec", false, "is_perfec");
//        public final static Property addresses = new Property(11, String.class, "addresses", false, "addresses");
//
//    };
//    @Override
//    protected void bindValues(SQLiteStatement stmt, Users entity) {
//        stmt.clearBindings();
//
//        Long id = entity.getUid();
//        if (id != null) {
//            stmt.bindLong(1, id);
//        }
//
//        String username = entity.getUsername();
//        if (username != null) {
//            stmt.bindString(2, username);
//        }
//
//        String truename = entity.getTruename();
//        if (truename != null ) {
//            stmt.bindString(3, truename);
//        }
//        String sex = entity.getSex();
//        if (sex != null) {
//            stmt.bindString(4, sex);
//        }
//        String marry = entity.getMarry();
//        if (marry != null) {
//            stmt.bindString(5, marry);
//        }
//        String mobile = entity.getMobile();
//        if (mobile != null ){
//            stmt.bindString(6, mobile);
//        }
//        String birthday = entity.getBirthday();
//        if (birthday != null) {
//            stmt.bindString(7, birthday);
//        }
//        String province = entity.getProvince();
//        if (province != null) {
//            stmt.bindString(8, province);
//        }
//        String province_nm = entity.getProvince_nm();
//        if (province_nm != null) {
//            stmt.bindString(9,province_nm);
//        }
//        String city = entity.getCity();
//        if (city != null) {
//            stmt.bindString(10, city);
//        }
//        String city_nm = entity.getCity_nm();
//        if (city_nm != null) {
//            stmt.bindString(11, city_nm);
//        }
//        String area = entity.getArea();
//        if (area !=null) {
//            stmt.bindString(12,area);
//        }
//        String area_nm = entity.getArea_nm();
//        if (area_nm != null) {
//            stmt.bindString(13, area_nm);
//        }
//        String email = entity.getEmail();
//        if (email != null){
//            stmt.bindString(14, email);
//        }
//        String balance = entity.getBalance();
//        if (balance != null) {
//            stmt.bindString(15, balance);
//        }
//        String user_pic = entity.getUser_pic();
//        if (user_pic != null) {
//            stmt.bindString(16, user_pic);
//        }
//        String is_perfec = entity.getIs_perfec();
//        if (is_perfec != null){
//            stmt.bindString(17, is_perfec);
//        }
//
//        String addresses = entity.getAddresses();
//        if (addresses != null){
//            stmt.bindString(18, addresses);
//        }
//    }
//
//    /** Creates the underlying database table. */
//    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
//        String constraint = ifNotExists? "IF NOT EXISTS ": "";
//        db.execSQL("CREATE TABLE " + constraint + "'USER' (" + //
//                "'uid' INTEGER PRIMARY KEY ," + // 0: id
//                "'username' TEXT," + // 1: imgsrc
//                "'truename' TEXT," + // 2: name
//                "'sex' TEXT," + // 3: baseprice
//                "'marry' TEXT," + // 4: basedistance
//                "'mobile' TEXT," + // 5: volume
//                "'birthday' TEXT," + // 6: size
//                "'province' TEXT," + // 7: unitprice
//                "'province_nm' TEXT," + // 8: cityid
//                "'city' TEXT," + // 9: waiteprice
//                "'city_nm' TEXT," + // 10: isLowPriceCarry
//                "'area' TEXT," + // 10: isLowPriceCarry
//                "'area_nm' TEXT," + // 10: isLowPriceCarry
//                "'email' TEXT," + // 10: isLowPriceCarry
//                "'balance' TEXT," + // 10: isLowPriceCarry
//                "'user_pic' TEXT," + // 10: isLowPriceCarry
//                "'is_perfec' TEXT," + // 10: isLowPriceCarry
//                "'addresses' TEXT);"); // 11: explainText
//    }
//
//    /** Drops the underlying database table. */
//    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
//        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'USER'";
//        db.execSQL(sql);
//    }
//    @Override
//    protected Users readEntity(Cursor cursor, int offset) {
//        Users entity = new Users(
//                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
//                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // imgsrc
//                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
//                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // baseprice
//                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // basedistance
//                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // volume
//                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // size
//                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // unitprice
//                cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // cityid
//                cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // waiteprice
//                cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // isLowPriceCarry
//                cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // explainText
//                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 12), // volume
//                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 13), // size
//                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 14), // unitprice
//                cursor.isNull(offset + 8) ? null : cursor.getString(offset + 15), // cityid
//                cursor.isNull(offset + 9) ? null : cursor.getString(offset + 16), // waiteprice
//                cursor.isNull(offset + 10) ? null : cursor.getString(offset + 17) // isLowPriceCarry
//        );
//        return entity;
//    }
//
//    @Override
//    protected Long readKey(Cursor cursor, int i) {
//        return cursor.isNull(i + 0) ? null : cursor.getLong(i + 0);
//
//}
//
//    @Override
//    protected void readEntity(Cursor cursor, Users entity, int offset) {
//        entity.setUid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
//        entity.setUsername(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
//        entity.setTruename(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
//        entity.setSex(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
//        entity.setMarry(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
//        entity.setMobile(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
//        entity.setBirthday(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
//        entity.setProvince(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
//        entity.setProvince_nm(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
//        entity.setCity(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
//        entity.setCity_nm(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
//        entity.setArea(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
//
//        entity.setArea_nm(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
//        entity.setEmail(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
//        entity.setBalance(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
//        entity.setUser_pic(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
//        entity.setIs_perfec(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
//        entity.setAddresses(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
//    }
//
//
//    @Override
//    protected Long updateKeyAfterInsert(Users users, long l) {
//        users.setUid(l);
//        return l;
//    }
//
//    @Override
//    protected Long getKey(Users users) {
//        if(users != null) {
//            return users.getUid();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    protected boolean isEntityUpdateable() {
//        return true;
//    }
//}
