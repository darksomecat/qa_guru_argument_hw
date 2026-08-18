package guru_qa.Test.ya;

 public enum LoginSubtext {

     RU("Войдите в аккаунт", "Чтобы слушать музыку и подкасты без ограничений"),
     EN("Log in to your account", "To listen to music and podcasts without restrictions"),
     UZ("Hisobga kiring", "Musiqa va podkastlarni cheklovlarsiz tinglash uchun"),
     KK("Аккаунтқа кіріңіз", "Музыка мен подкастарды шектеулерсіз тыңдау үшін");

     public final String headertext;
     public final String headersubtext;

     // Обязательно указываем String для КАЖДОГО параметра в конструкторе
     LoginSubtext(String headertext, String headersubtext) {
         this.headertext = headertext;
         this.headersubtext = headersubtext;
     }
 }
