package com.example.kidneyphrs;

/**
 * Created by pawitra on 2/18/2017 AD.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static com.example.kidneyphrs.FoodContent.CALORY;
import static com.example.kidneyphrs.FoodContent.CATEGORY;
import static com.example.kidneyphrs.FoodContent.FOOD_NAME;
import static com.example.kidneyphrs.FoodContent.PHOSPHORUS;
import static com.example.kidneyphrs.FoodContent.POTASSIUM;
import static com.example.kidneyphrs.FoodContent.PROTEIN;
import static com.example.kidneyphrs.FoodContent.SODIUM;
import static com.example.kidneyphrs.FoodContent.TABLE_NAME;



public class FoodHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "food.db";
    private static final int DATABASE_VERSION = 1;

    public FoodHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FOOD_NAME + " TEXT, " + CALORY + " INTEGER, " + CATEGORY + " INTEGER, " + PROTEIN + " DOUBLE, " + SODIUM + " DOUBLE, " + POTASSIUM + " DOUBLE, " + PHOSPHORUS + " DOUBLE);");


        //ต่อจาน
        addFoodItem(db, "ก๋วยจั๊บน้ำข้น", 368, 1, 21.60, 2668.00, 516.00, 267.00);
        addFoodItem(db, "ก๋วยเตี๋ยวคั่วไก่", 495, 1, 17.40, 961.00, 255.00, 219.00);
        addFoodItem(db, "ก๋วยเตี๋ยวน่องไก่ตุ๋นยาจีน", 423, 1, 30.20, 2217.00, 533.00, 311.00);
        addFoodItem(db, "ก๋วยเตี๋ยวผัดซีอิ้ว", 633, 1, 16.00, 1592.00, 594.00, 292.00);
        addFoodItem(db, "ก๋วยเตี๋ยวเย็นตาโฟ", 381, 1, 17.50, 2313.00, 391.00, 164.00);
        addFoodItem(db, "ก๋วยเตี๋ยวเส้นหมี่ลูกชิ้นน้ำใส", 244, 1, 14.50, 1786.00, 303.00, 438.00);
        addFoodItem(db, "ก๋วยเตี๋ยวลูกชิ้นหมูตุ๋น", 356, 1, 29.30, 2128.00, 447.00, 276.00);
        addFoodItem(db, "ก๋วยเตี๋ยวหลอด", 397, 1, 8.80, 1427.00, 473.00, 263.00);
        addFoodItem(db, "ก๋วยเตี๋ยวหมูต้มยำ", 514, 1, 27.60, 1434.00, 622.00, 335.00);
        addFoodItem(db, "ก๋วยเตี๋ยวหมูน้ำตก", 463, 1, 26.40, 2649.00, 621.00, 280.00);
        addFoodItem(db, "ขนมจีนน้ำเงี้ยว", 308, 1, 18.90, 1862.00, 648.00, 162.00);
        addFoodItem(db, "ขนมจีนแกงเขียวหวาน", 416, 1, 17.70, 1538.00, 702.00, 221.00);
        addFoodItem(db, "ขนมจีนน้ำยากะทิ", 346, 1, 10.80, 1578.00, 647.00, 158.00);
        addFoodItem(db, "ข้าวคลุกกะปิ", 565, 1, 20.50, 1999.00, 519.00, 260.00);
        addFoodItem(db, "ข้าวกล้องราดต้มจับฉ่าย", 345, 1, 12.50, 557.00, 474.00, 284.00);
        addFoodItem(db, "ข้าวกล้องราดลาบเห็ด", 442, 1, 16.40, 654.00, 265.00, 331.00);
        addFoodItem(db, "ข้าวผัดหมู", 581, 1, 22.70, 906.00, 212.00, 243.00);
        addFoodItem(db, "ข้าวราดแกงขี้เหล็ก", 352, 1, 11.90, 1547.00, 277.00, 138.00);
        addFoodItem(db, "ข้าวราดแกงเขียวหวานไก่", 313, 1, 15.90, 472.00, 221.00, 170.00);
        addFoodItem(db, "ข้าวราดแกงคั่วกลิ้งหมู", 378, 1, 9.40, 604.00, 335.00, 199.00);
        addFoodItem(db, "ข้าวราดแกงคั่วสับปะรด", 393, 1, 9.80, 1226.00, 369.00, 213.00);
        addFoodItem(db, "ข้าวราดแกงฉู่ฉี่ปลาทู", 307, 1, 13.00, 1082.00, 281.00, 138.00);
        addFoodItem(db, "ข้าวราดแกงไตปลา", 314, 1, 9.70, 990.00, 115.00, 109.00);
        addFoodItem(db, "ข้าวราดแกงเทโพหมูสามชั้น", 298, 1, 6.80, 1215.00, 332.00, 78.00);
        addFoodItem(db, "ข้าวราดแกงพะแนงหมู", 393, 1, 21.00, 1220.00, 371.00, 192.00);
        addFoodItem(db, "ข้าวราดแกงมัสมั่นไก่", 487, 1, 15.50, 1042.00, 478.00, 195.00);
        addFoodItem(db, "ข้าวราดแกงส้มชะอมทอด", 397, 1, 13.90, 1428.00, 308.00, 199.00);
        addFoodItem(db, "ข้าวราดแกงเหลือง", 339, 1, 11.80, 971.00, 188.00, 130.00);
        addFoodItem(db, "ข้าวราดไข่เจียวหมูสับ", 808, 1, 27.90, 684.00, 367.00, 341.00);
        addFoodItem(db, "ข้าวราดต้มกะทิสายบัว", 381, 1, 10.70, 1637.00, 388.00, 117.00);
        addFoodItem(db, "ข้าวราดต้มจืดเต้าหู้หมูสับ", 263, 1, 21.20, 1282.00, 364.00, 213.00);
        addFoodItem(db, "ข้าวราดต้มหมูพะโล้กับไข่", 535, 1, 25.10, 1271.00, 266.00, 258.00);
        addFoodItem(db, "ข้าวราดทอดมันปลากราย", 618, 1, 23.10, 974.00, 279.00, 253.00);
        addFoodItem(db, "ข้าวราดผัดกระเพราไก่", 469, 1, 24.20, 1880.00, 379.00, 225.00);
        addFoodItem(db, "ข้าวราดผัดคะน้าหมูกรอบ", 546, 1, 18.20, 973.00, 435.00, 203.00);
        addFoodItem(db, "ข้าวราดผัดฉ่าปลากระเบน", 422, 1, 28.00, 1416.00, 921.00, 176.00);
        addFoodItem(db, "ข้าวราดผัดฉ่าปลากะพง", 474, 1, 18.10, 1408.00, 382.00, 159.00);
        addFoodItem(db, "ข้าวราดผัดฉ่าปลาดุก", 515, 1, 26.80, 1799.00, 709.00, 335.00);
        addFoodItem(db, "ข้าวราดผัดเต้าหู้ไข่ทรงเครื่อง", 372, 1, 15.20, 1692.00, 351.00, 218.00);
        addFoodItem(db, "ข้าวราดผัดผักรวม", 332, 1, 8.80, 1352.00, 239.00, 100.00);
        addFoodItem(db, "ข้าวราดผัดเผ็ดไก่กับหน่อไม้ดอง", 402, 1, 14.00, 1149.00, 352.00, 146.00);
        addFoodItem(db, "ข้าวราดผัดเผ็ดปลาดุก", 474, 1, 20.80, 1903.00, 598.00, 258.00);
        addFoodItem(db, "ข้าวราดผัดเผ็ดหอยแมลงภู่", 424, 1, 17.60, 1229.00, 94.00, 212.00);
        addFoodItem(db, "ข้าวราดผัดเผ็ดหอยลาย", 307, 1, 5.70, 850.00, 231.00, 107.00);
        addFoodItem(db, "ข้าวราดผัดฟักทองกับไข่", 439, 1, 13.20, 1116.00, 559.00, 202.00);
        addFoodItem(db, "ข้าวราดผัดถั่วงอกกับเต้าหู้", 385, 1, 12.40, 1513.00, 171.00, 160.00);
        addFoodItem(db, "ข้าวราดผัดมะเขือยาวกับหมูสับ", 440, 1, 9.70, 823.00, 369.00, 112.00);
        addFoodItem(db, "ข้าวราดผัดมะระกับไข่", 522, 1, 15.00, 462.00, 314.00, 217.00);
        addFoodItem(db, "ข้าวราดยำไข่ดาว ", 638, 1, 19.90, 1713.00, 254.00, 349.00);
        addFoodItem(db, "ข้าวราดยำหมูยอ", 456, 1, 13.80, 1589.00, 268.00, 150.00);
        addFoodItem(db, "ข้าวราดหมูทอดกระเทียม", 441, 1, 13.90, 608.00, 243.00, 201.00);
        addFoodItem(db, "ข้าวมันไก่", 619, 1, 10.90, 1251.00, 213.00, 95.00);
        addFoodItem(db, "ข้าวหมกไก่", 551, 1, 24.20, 1430.00,408.00 , 262.00);
        addFoodItem(db, "ข้าวหมูแดง", 521, 1, 21.90, 1307.00, 473.00, 357.00);
        addFoodItem(db, "ข้าวหมูอบ", 432, 1, 19.70, 1354.00, 425.00, 188.00);
        addFoodItem(db, "บะหมี่เกี๊ยวหมูแดง", 332, 1, 17.50, 1925.00, 447.00, 141.00);
        addFoodItem(db, "บะหมี่แห้งหมู", 463, 1, 26.60, 2377.00, 324.00, 207.00);
        addFoodItem(db, "ผัดไทกุ้งสด", 486, 1, 20.90, 1060.00, 417.00, 317.00);
        addFoodItem(db, "ลาซานญ่าเนื้อ", 845, 1, 49.49, 2130.00, 1165.00, 813.00);
        addFoodItem(db, "สเต็กเนื้อสันนอก", 324, 1, 49.47, 563.00, 629.00, 382.00);
        addFoodItem(db, "สุกี้ไก่น้ำ", 253, 1, 20.30, 3542.00, 436.00, 222.00);
        addFoodItem(db, "สุกี้รวมมิตรน้ำ", 207, 1, 20.9, 3026.00, 482.00, 247.00);
        addFoodItem(db, "สุกี้รวมมิตรแห้ง", 399, 1, 21.90, 1978.00, 439.00, 232.00);
        addFoodItem(db, "เส้นใหญ่ผัดซีอิ้ว", 633, 1, 16.00, 1592.00, 594.00, 292.00);
        addFoodItem(db, "เส้นใหญ่ราดหน้าหมู", 506, 1, 16.50, 1753.00, 497.00, 190.00);
        addFoodItem(db, "หมี่กรอบราดหน้าทะเล", 457, 1, 14.30, 2051.00, 332.00, 142.00);
        addFoodItem(db, "หอยทอด", 812, 1, 20.20, 1450.00, 284.00, 462.00);
        addFoodItem(db, "แฮมเบอร์เกอร์", 251, 1, 12.27, 469.00, 182.00, 102.00);

        //คิดต่อจาน
        addFoodItem(db, "กุ้งชุบเกล็ดขนมปังทอด", 521, 2, 21.41, 1901.00, 183.00, 509.00);
        addFoodItem(db, "เกาเหลาเนื้อตุ๋น", 149, 2, 13.90, 1197.00, 172.00, 83.00);
        addFoodItem(db, "เกาเหลาหมู", 163, 2, 15.20, 1878.00, 256.00, 119.00);
        addFoodItem(db, "แกงเลียง", 119, 2, 5.60, 11.00, 665.00, 341.00);
        addFoodItem(db, "ซุปเนื้อวัว", 214, 2, 41.60, 2867.00, 457.00, 413.00);
        addFoodItem(db, "ต้มเลือดหมู", 216, 2, 30.30, 2010.00, 719.00, 120.00);
        addFoodItem(db, "เต้าเจี้ยวหลน+ผักสด ", 432, 2, 15.10, 2077.00, 862.00, 236.00);
        addFoodItem(db, "น้ำพริกกะปิ+ปลาทู+ผัก", 386, 2, 32.80, 1673.00, 725.00, 0.00);
        addFoodItem(db, "ปลาชุบเกล็ดขนมปังทอด", 495, 2, 30.49, 1268.00, 567.00, 468.00);
        addFoodItem(db, "ปลาทู 3 รส", 203, 2, 15.70, 1142.00, 415.00, 208.00);
        addFoodItem(db, "ลาบปลาดุก", 167, 2, 13.40, 539.00, 208.00, 258.00);
//        addFoodItem(db, "ลาซานญ่าเนื้อ", , , , , , );


        // คิดต่อ 100 กรัม
        addFoodItem(db, "ขนมกล้วยทอด", 519, 3, 2.30, 6.00, 536.00, 56.00);
        addFoodItem(db, "ขนมขบเคี้ยวงา", 516, 3, 11.60, 167.00, 307.00, 412.00);
        addFoodItem(db, "ขนมขี้มอด", 460, 3, 3.90, 237.00, null, 64.00);
        addFoodItem(db, "ขนมชั้น",276, 3, 0.80, 54.00, null,16.00);
        addFoodItem(db, "ขนมตังเมกับอัลมอนด์", 398, 3, 3.33, 33.00, 105.00, 55.00);
        addFoodItem(db, "ขนมลา", 439, 3, 2.60, 93.00, null, 40.00);
        addFoodItem(db, "ขนมหม้อแกงไข่", 204, 3, 6.30, 122.00, null, 60.00);
        addFoodItem(db, "ขนมหม้อแกงถั่ว", 216, 3, 5.90, 401.00, null, 72.00);
        addFoodItem(db, "ทองหยอด", 340, 3, 4.30, 47.00, null, 92.00);
        addFoodItem(db, "ฝอยทอง", 431, 3, 13.50, 63.00, null, 205.00);
        addFoodItem(db, "พายแอปเปิ้ล", 249, 3, 2.36, 153.00, 49.00, 28.00);
        addFoodItem(db, "พุดดิ้งกล้วย", 366, 3, 0.00, 788.00, 17.00, 5.00);
        addFoodItem(db, "พุดดิ้งครีมมะพร้าว", 343, 3, 1.00, 682.00, 133.00, 35.00);
        addFoodItem(db, "พุดดิ้งช็อคโกแลต", 362, 3, 2.60, 479.00, 209.00, 88.00);
        addFoodItem(db, "พุดดิ้งวานิลลา", 379, 3, 0.30, 635.00, 20.00, 2.00);
        addFoodItem(db, "มันฝรั่งทอด", 229, 3, 2.42, 134.00, 423.00, 90.00);
        addFoodItem(db, "มันฝรั่งบด", 215, 3, 3.99, 741.00, 692.00, 143.00);
        addFoodItem(db, "มาร์ชเมลโลว์", 318, 3, 1.80, 80.00, 5.00, 8.00);
        addFoodItem(db, "เม็ดขนุน", 374, 3, 9.80, 44.00, null, 210.00);
        addFoodItem(db, "เยลลี่", 266, 3, 0.15, 30.00, 54.00, 6.00);
        addFoodItem(db, "ลอดช่องน้ำกะทิ",133, 3, 0.80, 28.00, null, 19.00);
        addFoodItem(db, "ไอศครีมช็อคโกแลต", 216, 3, 3.80, 76.00, 249.00, 107.00);
        addFoodItem(db, "ไอศครีมวานิลลา", 207, 3, 3.50, 80.00, 199.00, 105.00);
        addFoodItem(db, "ไอศครีมสตรอเบอร์รี่", 192, 3, 3.20, 60.00, 188.00, 100.00);
//        addFoodItem(db, "มันฝรั่งทอด", , , , , , );


        // คิดต่อหน่วย 100 กรัม
        addFoodItem(db, "กล้วย", 89, 4, 1.09, 1.00, 385.00, 22.00);
        addFoodItem(db, "กีวี", 61, 4, 1.14, 3.00, 312.00, 34.00);
        addFoodItem(db, "แคนตาลูป", 33, 4, 0.80, 4.20, 162.00, 6.30);
        addFoodItem(db, "แครนเบอร์รี่", 46, 4, 0.46, 2.00, 80.00, 11.00);
        addFoodItem(db, "เงาะ", 82, 4, 0.90, 22.00, 9.00, 6.00);
        addFoodItem(db, "เชอร์รี่", 50, 4, 1.00, 3.00, 173.00, 15.00);
        addFoodItem(db, "แตงโม", 30, 4, 0.61, 1.00, 112.00, 11.00);
        addFoodItem(db, "ทับทิม", 83, 4, 1.67, 3.00, 236.00, 36.00);
        addFoodItem(db, "ทุเรียน", 147, 4, 1.47, 2.00, 436.00, 39.00);
        addFoodItem(db, "บลูเบอร์รี่", 57, 4, 0.74, 1.00, 77.00, 12.00);
        addFoodItem(db, "แบล็คเบอรี่", 43, 4, 1.39, 1.00, 162.00, 22.00);
        addFoodItem(db, "ฝรั่ง", 43, 4, 0.60, 1.80, 174.00, 14.30);
        addFoodItem(db, "พลัม", 46, 4, 0.70, 0.00, 157.00, 16.00);
        addFoodItem(db, "มะขาม", 239, 4, 2.80, 28.00, 628.00, 113.00);
        addFoodItem(db, "มะเฟือง", 31, 4, 1.04, 2.00, 133.00, 12.00);
        addFoodItem(db, "มะม่วง", 87, 4, 0.70, 1.50, 197.00, 27.80);
        addFoodItem(db, "ราสเบอร์รี่", 52, 4, 1.20, 1.00, 151.00, 29.00);
        addFoodItem(db, "ละมุด", 83, 4, 0.44, 12.00, 193.00, 12.00);
        addFoodItem(db, "ลำไย", 60, 4, 1.31, 0.00, 266.00, 21.00);
        addFoodItem(db, "ลิ้นจี่", 66, 4, 0.83, 1.00, 171.00, 31.00);
        addFoodItem(db, "ส้ม", 53, 4, 0.81, 2.00, 166.00, 20.00);
        addFoodItem(db, "ส้มโอ", 38, 4, 0.76, 1.00, 216.00, 17.00);
        addFoodItem(db, "สตรอเบอร์รี่", 32, 4, 0.67, 1.00, 153.00, 24.00);
        addFoodItem(db, "สับปะรด", 50, 4, 0.54, 1.00, 109.00, 8.00);
        addFoodItem(db, "องุ่น", 69, 4, 0.72, 2.00, 191.00, 20.00);
        addFoodItem(db, "อะโวคาโด", 160, 4, 2.00, 7.00, 485.00, 52.00);
        addFoodItem(db, "ลูกพลับ", 70, 4, 0.58, 1.00, 161.00, 17.00);
        addFoodItem(db, "ลูกแพร", 57, 4, 0.36, 1.00, 116.00, 12.00);
        addFoodItem(db, "แอปเปิ้ล", 59, 4, 0.27, 1.00, 104.00, 12.00);


        // คิดต่อหน่วย 100 กรัม
        addFoodItem(db, "กาแฟ", 2, 5, 0.30, 1.00, 50.00, 3.00);
        addFoodItem(db, "ชาเขียว", 30, 5, 0.00, 2.00, 5.00, 0.00);
        addFoodItem(db, "น้ำช็อกโกแลต", 89, 5, 3.27, 50.00, 144.00, 84.00);
        addFoodItem(db, "น้ำผึ้ง", 304, 5, 0.30, 4.00, 52.00, 4.00);
        addFoodItem(db, "น้ำมะพร้าว", 18, 5, 0.22, 26.00, 165.00, 5.00);
        addFoodItem(db, "น้ำมะนาว", 376, 5, 0.00, 51.00, 147.00, 4.00);
        addFoodItem(db, "น้ำส้ม", 48, 5, 0.00, 12.00, 2.00, 1.00);
        addFoodItem(db, "น้ำอัดลม", 42, 5, 0.00, 3.00, 5.00, 9.00);
        addFoodItem(db, "เบียร์", 43, 5, 0.46, 4.00, 27.00, 14.00);
        addFoodItem(db, "เป็ปซี่", 8, 5, 0.05 , 45.00, 13.00, 7.00);
        addFoodItem(db, "มอคค่า", 460, 5, 5.29, 317.00, 1033.00, 251.00);
        addFoodItem(db, "ไวน์", 85, 5, 0.07, 4.00, 127.00, 23.00);
        addFoodItem(db, "เอสเปรสโซ่", 9, 5, 0.12, 14.00, 115.00, 7.00);
        addFoodItem(db, "โอวันติน", 372, 5, 0.00, 636.00, 0.00, 0.00);

//        addFoodItem(db, "", , , , , , );
//        addFoodItem(db, "", , , , , , );
//        addFoodItem(db, "", , , , , , );
//        addFoodItem(db, "", , , , , , );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void addFoodItem(SQLiteDatabase db, String foodName, Integer calory, Integer category, Double protein, Double sodium, Double potassium, Double phosphorus){
        ContentValues values = new ContentValues();
        values.put(FOOD_NAME , foodName);
        values.put(CALORY, calory);
        values.put(CATEGORY , category);
        values.put(PROTEIN , protein);
        values.put(SODIUM , sodium);
        values.put(POTASSIUM , potassium);
        values.put(PHOSPHORUS , phosphorus);
        db.insert(TABLE_NAME, null, values);



    }
}
