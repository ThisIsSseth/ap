package ap.exercises.MidTermLib.LM;

import ap.exercises.MidTermLib.LM.Members.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private Manager manager;
    private String libName;
    private Map<Integer, Book> bookMap = new HashMap<>();
    private Map<Integer,Student> studentMap = new HashMap<>();
    private Map<Integer,Operator> operatorMap = new HashMap<>();
    private List<Borrow> borrowList = new ArrayList<>();


    public Library(String libName) {
        this.libName = libName;
    }

    public Manager getManager() {
        return new Manager(manager.getFirstName(), manager.getLastName(), manager.getId(), manager.getEducation());
    }

    public String getLibName() {
        return libName;
    }

    public boolean managerPasswordCheck(int password) {
        return manager.passwordCheck(password);
    }

    public Map<Integer, Student> getStudentMap() {
        return studentMap;
    }

    public Map<Integer, Operator> getOperatorMap() {
        return operatorMap;
    }

    public Map<Integer,Book> getBookMap() {
        return bookMap;
    }

    @Override
    public String toString() {
        return this.libName + "*" ;
    }

    public List<Borrow> getBorrowMap() {
        return borrowList;
    }

}

/*
* پروژه میانترم - سیستم مدیریت کتابخانه
زمان تحویل روز امتحان میانترم
هدف طراحی و پیاده سازی کلاس های کتابخانه دانشگاه میباشد. دانشجو باید بتواند در کتاب خانه عضو شود. بین کتاب ها جستجو کند و کتاب را به امانت ببرد و سپس آن را برگرداند. فرایند به امانت بردن و تحویل دادن کتاب توسط متصدی کتابخانه انجام می شود. کتابخانه یک مدیری دارد که می تاند گزارشاتی را از سیستم دریافت کند مانند: لیست کتاب هایی که امانت گرفته شده و دیرتر از زمان در نظر گفته شده برگردانده شده اند. تعداد تحویل و دریافت کتاب ها به دانشجویان به ازای هر متصدی و اطلاعات ۱۰ کتابی که در یکسال اخیر بیشترین امانات را داشته اند.
در سیستم کتابخانه باید با شروع مجدد برنامه همه اطلاعات آخرین اجرای قبل در فایل ذخیره شده باشد.
در شروع باید یک کتابخانه بدون کتاب و دانشجو صرفا با یک مدیر و ۲ متصدی ایجاد گردد. در ادامه دانشجو میتواند در قالب ثبت نام در کتابخانه شود. مدیر میتواند اطلاعات کتاب جدید را ثبت و به کتابخانه اضافه کند. در فرایند امانت دادن متصدی امانت دهنده یا تحویل گیرنده به صورت تصادفی انتخاب می شود.


راهنمایی اولیه تعریف کلاس ها:
۱- تعریف کلاس کتاب (هر کتاب شامل ویژگی های عنوان، نویسنده، سال انتشار، تعداد صفحات) - در تمرین سری سوم انجام شده

۲- تعریف کلاس دانشجو (هر دانشجو شامل ویژگی های نام، نام خانوادگی، شماره دانشجویی و رشته تحصیلی، تاریخ عضویت) - در تمرین سری سوم انجام شده فقط باید تاریخ عضویت اضافه گردد

۳- تعریف کلاس متصدی کتابخانه (هر متصدی شامل ویزگی های نام و نام خانوادگی، شناسه کارمندی)

۴- تعریف کلاس امانت (هر امانت شامل یک کتاب که به امانت گرفته شده، یک دانشجو که کتاب را به امانت برده، یک متصدی کتابخانه که کتاب را به دانشجو تحویل و امانت را ثبت کرده و تاریخ امانت داده شده به همراه تاریخی که باید برگشت داده شود - یک ویژگی هم باید برای تاریخ واقعی که دانشجو تحویل داده در نظر گرفته شود همچنین متصدی که که کتاب را تحویل گرفته)

۵- مدیر کتابخانه (شامل نام و نام خانوادگی و سطح تحصیلات)

۶- تعریف کلاس کتابخانه (شامل نام کتابخانه، لیست کتابها، لیست دانشجویان عضو، لیست متصدیان و لیست امانات)

۷- تعریف کلاس ذخیره و بازیابی اطلاعات کتابخانه در یک یا چند فایل

۸- تعریف کلاسی منو (شامل لیست کارهای قابل انجام توسط دانشجو و مدیر)

۹- تعریف کلاس دریافت و پردازش ورودی ها

۱۰- تعریف کلاس Main که باید کارهای زیر به واسطه کلاس های تعریف شده در آن انجام شود:
- ایجاد نمونه از کلاس کتابخانه
- دریافت نوع کاربر از ورودی (دانشجو یا مدیر)
- ارائه لیست کارهای قابل انجام توسط نوع کاربرد در قالب منو
- انجام هر کدام از کارها به واسطه تعریف متد مناسب در کلاس های تعریف شده در حلقه*/
