function createjsDOMenu() {
  staticMenu1 = new jsDOMenu(171, "static", "staticMenu", true);
  with (staticMenu1) {
    //addMenuItem(new menuItem("คู่มือการใช้งาน", "item_manual", "../../ebook/chantra-manual.pdf"));
    addMenuItem(new menuItem("แนะนำ", "item_navigate"));
    addMenuItem(new menuItem("<b>โปรแกรม</b>", "item_program"));
    addMenuItem(new menuItem("สำนักงาน", "item_office"));
    addMenuItem(new menuItem("อินเทอร์เน็ต", "item_internet"));
    addMenuItem(new menuItem("กราฟิกส์", "item_graphics"));
    addMenuItem(new menuItem("มัลติมีเดีย", "item_multimedia"));
    addMenuItem(new menuItem("อรรถประโยชน์", "item_utilities"));
    addMenuItem(new menuItem("พัฒนาเว็บ", "item_webdevel"));
    addMenuItem(new menuItem("บันเทิง", "item_entertainment"));
    addMenuItem(new menuItem("การศึกษา", "item_education"));
    show();
  }

  menu_navigate = new jsDOMenu(200, "absolute");
  with (menu_navigate) {
    addMenuItem(new menuItem("<font color = 'black'>ยินดีต้อนรับ</font>", "item_home", "home_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>มีอะไรใหม่</font>", "item_whatsnew", "whatsnew_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>ข้อตกลง</font>", "item_legal", "legal_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>โอเพนซอร์ส</font>", "item_opensource", "opensource_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>เว็บไซต์</font>", "item_website", "web_desc.html"));
  }
  
  menu_office = new jsDOMenu(260, "absolute");
  with (menu_office) {
    addMenuItem(new menuItem("<font color = 'black'>OpenOffice.org - Office Suite</font>", "item_openoffice", "openoffice_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Dia - Diagram Drawing</font>", "item_dia", "dia_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>PDFCreator - Print to PDF</font>", "item_pdfcreator", "pdfcreator_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>FreeMind - Organizing Ideas</font>", "item_freemind", "freemind_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>GanttProject - Project Management</font>", "item_ganttproject", "ganttproject_desc.html"));
  }
  
  menu_internet = new jsDOMenu(220, "absolute");
  with (menu_internet) {
    addMenuItem(new menuItem("<font color = 'black'>Firefox - Browser</font>", "item_firefox", "firefox_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Thunderbird - Email</font>", "item_thunderbird", "thunderbird_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Pidgin - Instant Messenger</font>", "item_pidgin", "pidgin_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>BitTorrent - Peer to Peer</font>", "item_bittorrent", "bittorrent_desc.html"));
  }
  
  menu_graphics = new jsDOMenu(260, "absolute");
  with (menu_graphics) {
    addMenuItem(new menuItem("<font color = 'black'>Blender - 3D Graphics</font>", "item_blender", "blender_desc.html"));
	addMenuItem(new menuItem("<font color = 'black'>GIMP - Bitmap Graphics</font>", "item_gimp", "gimp_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Inkscape - Vector Drawing</font>", "item_inkscape", "inkscape_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Open Clip Art - Clip Art Collection</font>", "item_openclipart", "openclipart_desc.html"));
  }
  
  menu_multimedia = new jsDOMenu(200, "absolute");
  with (menu_multimedia) {
    addMenuItem(new menuItem("<font color = 'black'>VLC - Media Player</font>", "item_vlc", "vlc_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Audacity - Sound Editor</font>", "item_audacity", "audacity_desc.html"));
  }
  
  menu_utilities = new jsDOMenu(230, "absolute");
  with (menu_utilities) {
	addMenuItem(new menuItem("<font color = 'black'>InfraRecorder - CD Burning </font>", "item_infrarecorder", "infra_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>7-Zip - Archiver</font>", "item_7zip", "7zip_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>ClamWin - Anti-virus</font>", "item_clamwin", "clamwin_desc.html"));
  }

  menu_webdevel = new jsDOMenu(330, "absolute");
  with (menu_webdevel) {
    addMenuItem(new menuItem("<font color = 'black'>Kompozer - Web Authoring</font>", "item_kompozer", "kompozer_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>FileZilla - File Transfer</font>", "item_filezilla", "filezilla_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>PuTTY - Secure Remote Login</font>", "item_putty", "putty_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Notepad2 - Text Editor</font>", "item_notepad2", "notepad2_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>AppServ - Web Server</font>", "item_appserv", "appserv_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Subversion - Version Control</font>", "item_subversion", "subversion_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Joomla - Content Management System</font>", "item_joomla", "joomla_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Moodle - Learning Management System</font>", "item_moodle", "moodle_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>SMF - Message Board</font>", "item_smf", "smf_desc.html"));
  }
  
  menu_entertainment = new jsDOMenu(240, "absolute");
  with (menu_entertainment) {
    addMenuItem(new menuItem("<font color = 'black'>LBreakout2 - Arcade Game</font>", "item_lbreakout2", "lbreakout2_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>SuperTux - Side-scroller Game</font>", "item_supertux", "supertux_desc.html"));
  }
  
  menu_education = new jsDOMenu(250, "absolute");
  with (menu_education) {
    addMenuItem(new menuItem("<font color = 'black'>Celestia - Space Simulation</font>", "item_celestia", "celestia_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>GCompris - Children Activities</font>", "item_gcompris", "gcompris_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Tux Paint - Simple Painting</font>", "item_tuxpaint", "tuxpaint_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>Tux Typing - Typing Tutor</font>", "item_tuxtyping", "tuxtyping_desc.html"));
    addMenuItem(new menuItem("<font color = 'black'>TuxMath - Math Tutor</font>", "item_tuxmath", "tuxmath_desc.html"));
  }

  staticMenu1.items.item_program.setClassName("jsdomenuitemheader");
  staticMenu1.items.item_program.setClassNameOver("jsdomenuitemheader");
  staticMenu1.items.item_navigate.setSubMenu(menu_navigate);
  staticMenu1.items.item_office.setSubMenu(menu_office);
  staticMenu1.items.item_graphics.setSubMenu(menu_graphics);
  staticMenu1.items.item_internet.setSubMenu(menu_internet);
  staticMenu1.items.item_multimedia.setSubMenu(menu_multimedia);
  staticMenu1.items.item_utilities.setSubMenu(menu_utilities);
  staticMenu1.items.item_webdevel.setSubMenu(menu_webdevel);
  staticMenu1.items.item_entertainment.setSubMenu(menu_entertainment);
  staticMenu1.items.item_education.setSubMenu(menu_education);
  
  //staticMenu1.items.item_manual.showIcon("icon_manual");
  staticMenu1.items.item_navigate.showIcon("icon_navigate");
  staticMenu1.items.item_office.showIcon("icon_office");
  staticMenu1.items.item_graphics.showIcon("icon_graphics");
  staticMenu1.items.item_internet.showIcon("icon_internet");
  staticMenu1.items.item_multimedia.showIcon("icon_multimedia");
  staticMenu1.items.item_utilities.showIcon("icon_utilities");
  staticMenu1.items.item_webdevel.showIcon("icon_webdevel");
  staticMenu1.items.item_entertainment.showIcon("icon_entertainment");
  staticMenu1.items.item_education.showIcon("icon_education");
   
  menu_navigate.items.item_home.showIcon("icon_home");
  menu_navigate.items.item_whatsnew.showIcon("icon_star");
  menu_navigate.items.item_legal.showIcon("icon_legal");
  menu_navigate.items.item_opensource.showIcon("icon_opensource");
  menu_navigate.items.item_website.showIcon("icon_website");
    
  menu_office.items.item_openoffice.showIcon("icon_openoffice");
  menu_office.items.item_dia.showIcon("icon_dia");
  menu_office.items.item_pdfcreator.showIcon("icon_pdfcreator");
  menu_office.items.item_freemind.showIcon("icon_freemind");
  menu_office.items.item_ganttproject.showIcon("icon_ganttproject");

  menu_internet.items.item_firefox.showIcon("icon_firefox");
  menu_internet.items.item_thunderbird.showIcon("icon_thunderbird");
  menu_internet.items.item_pidgin.showIcon("icon_pidgin");
  menu_internet.items.item_bittorrent.showIcon("icon_bittorrent");

  menu_graphics.items.item_blender.showIcon("icon_blender"); 
  menu_graphics.items.item_gimp.showIcon("icon_gimp");
  menu_graphics.items.item_inkscape.showIcon("icon_inkscape");
  menu_graphics.items.item_openclipart.showIcon("icon_openclipart");

  menu_multimedia.items.item_vlc.showIcon("icon_vlc");
  menu_multimedia.items.item_audacity.showIcon("icon_audacity");
  
  menu_utilities.items.item_infrarecorder.showIcon("icon_infra");
  menu_utilities.items.item_7zip.showIcon("icon_7zip");
  menu_utilities.items.item_clamwin.showIcon("icon_clamwin");

  menu_webdevel.items.item_kompozer.showIcon("icon_kompozer");
  menu_webdevel.items.item_filezilla.showIcon("icon_filezilla");
  menu_webdevel.items.item_notepad2.showIcon("icon_notepad2");
  menu_webdevel.items.item_putty.showIcon("icon_putty");
  menu_webdevel.items.item_appserv.showIcon("icon_appserv");
  menu_webdevel.items.item_subversion.showIcon("icon_subversion");
  menu_webdevel.items.item_joomla.showIcon("icon_joomla");
  menu_webdevel.items.item_moodle.showIcon("icon_moodle");
  menu_webdevel.items.item_smf.showIcon("icon_smf");

  menu_entertainment.items.item_lbreakout2.showIcon("icon_lbreakout2");
  menu_entertainment.items.item_supertux.showIcon("icon_supertux");
  
  menu_education.items.item_celestia.showIcon("icon_celestia");
  menu_education.items.item_gcompris.showIcon("icon_gcompris");
  menu_education.items.item_tuxpaint.showIcon("icon_tuxpaint");
  menu_education.items.item_tuxtyping.showIcon("icon_tuxtyping");
  menu_education.items.item_tuxmath.showIcon("icon_tuxmath");
}
