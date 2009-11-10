; Script generated by the HM NIS Edit Script Wizard.

; HM NIS Edit Wizard helper defines
!define PRODUCT_NAME "Moodle"
!define PRODUCT_VERSION "1.9.6"
!define PRODUCT_PUBLISHER "Moodle"
!define PRODUCT_WEB_SITE "http://www.moodle.org"
;!define PRODUCT_DIR_REGKEY "Software\Microsoft\Windows\CurrentVersion\App Paths\mimetex.exe"
!define PRODUCT_UNINST_KEY "Software\Microsoft\Windows\CurrentVersion\Uninstall\${PRODUCT_NAME}"
!define PRODUCT_UNINST_ROOT_KEY "HKLM"

; MUI 1.67 compatible ------
!include "MUI.nsh"

; MUI Settings
!define MUI_ABORTWARNING
!define MUI_ICON "${NSISDIR}\Contrib\Graphics\Icons\orange-install.ico"
!define MUI_UNICON "${NSISDIR}\Contrib\Graphics\Icons\orange-uninstall.ico"

; Welcome page
!insertmacro MUI_PAGE_WELCOME
; License page
!insertmacro MUI_PAGE_LICENSE "C:\xampp\htdocs\moodle\COPYING.txt"
; Instfiles page
!insertmacro MUI_PAGE_INSTFILES
; Finish page
!insertmacro MUI_PAGE_FINISH

; Uninstaller pages
!insertmacro MUI_UNPAGE_INSTFILES

; Language files
!insertmacro MUI_LANGUAGE "English"

; MUI end ------

Name "${PRODUCT_NAME} ${PRODUCT_VERSION}"
OutFile "xampp-addon-moodle-1.9.6-setup.exe"
InstallDir "$PROGRAMFILES\Moodle"
InstallDirRegKey HKLM "${PRODUCT_DIR_REGKEY}" ""
ShowInstDetails show
ShowUnInstDetails show

; Request application privileges for Windows Vista
RequestExecutionLevel admin

Function .onInit
   ; read registry
   ReadRegStr $INSTDIR HKLM Software\xampp "Install_Dir"
   StrCmp $INSTDIR "" 0 NoAbort
     MessageBox MB_OK "XAMPP not found. Unable to get install path."
     Abort ; causes installer to quit.
   NoAbort:
FunctionEnd

Section "MainSection" SEC01
  ; overide InstallDir
  ReadRegStr $INSTDIR HKLM Software\xampp "Install_Dir"
  SetOutPath "$INSTDIR\htdocs"
  SetOverwrite try
  File /r "C:\xampp\htdocs\moodle"

SectionEnd

Section "DatabaseSection" SEC02
  ; overide InstallDir
  ReadRegStr $INSTDIR HKLM Software\xampp "Install_Dir"
  SetOutPath "$INSTDIR\mysql\data"
  File /r "C:\xampp\mysql\data\moodle"
SectionEnd


Section -AdditionalIcons
  WriteIniStr "$INSTDIR\htdocs\moodle\${PRODUCT_NAME}.url" "InternetShortcut" "URL" "${PRODUCT_WEB_SITE}"
  CreateDirectory "$SMPROGRAMS\Moodle"
  CreateShortCut "$SMPROGRAMS\Moodle\Website.lnk" "$INSTDIR\htdocs\moodle\${PRODUCT_NAME}.url"
  CreateShortCut "$SMPROGRAMS\Moodle\Uninstall.lnk" "$INSTDIR\htdocs\moodle\uninst.exe"
SectionEnd

Section -Post
  WriteUninstaller "$INSTDIR\htdocs\moodle\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayName" "$(^Name)"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "UninstallString" "$INSTDIR\htdocs\moodle\uninst.exe"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "DisplayVersion" "${PRODUCT_VERSION}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "URLInfoAbout" "${PRODUCT_WEB_SITE}"
  WriteRegStr ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}" "Publisher" "${PRODUCT_PUBLISHER}"
SectionEnd


Function un.onUninstSuccess
  HideWindow
  MessageBox MB_ICONINFORMATION|MB_OK "$(^Name) was successfully removed from your computer."
FunctionEnd

Function un.onInit
  MessageBox MB_ICONQUESTION|MB_YESNO|MB_DEFBUTTON2 "Are you sure you want to completely remove $(^Name) and all of its components?" IDYES +2
  Abort
FunctionEnd

Section Uninstall
  ; overide InstallDir
  ReadRegStr $INSTDIR HKLM Software\xampp "Install_Dir"
  RMDir /r "$INSTDIR\htdocs\moodle"
  RMDir /r "$INSTDIR\mysql\data\moodle"
  RMDir /r "$SMPROGRAMS\Moodle"

  DeleteRegKey ${PRODUCT_UNINST_ROOT_KEY} "${PRODUCT_UNINST_KEY}"
;  DeleteRegKey HKLM "${PRODUCT_DIR_REGKEY}"
  SetAutoClose true
SectionEnd