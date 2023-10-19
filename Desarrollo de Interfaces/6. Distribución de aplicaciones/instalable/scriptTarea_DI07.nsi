;Utilizamos Modern UI
!include "MUI2.nsh"

;Estructura General
Name "Instalacion de Tarea DI07 Distribucion de Aplicaciones" ;Título del instalador.
Outfile "Instalador_Tarea_DI07.exe" ;Fichero que vamos a generar y utilizar como instalador.
!define MUI_ICON "Logo_DI07.ico"
Unicode True
InstallDir "C:\Program Files (x86)\Tarea_DI07" ;Carpeta donde se instalará la aplicación por defecto.
InstallDirRegKey HKCU "Software\Tarea_DI07" "" ;Definimos la clave en el registro.
RequestExecutionLevel admin ;Privilegios para el instalador. Admite los valores user o admin.
!define MUI_ABORTWARNING

;Definimos las páginas o ventanas
!insertmacro MUI_PAGE_COMPONENTS
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES

;Configuramos el idioma del instalador
!insertmacro MUI_LANGUAGE "Spanish"

;Definimos el componente que se va a poder señalar para instalar.
Section "Tarea_DI07 jar" SecDummy
    SetOutPath "$INSTDIR"
    File Tarea_DI07.jar
    SetOutPath $INSTDIR\lib ;Creamos la carpeta lib en el directorio en donde se va a instalar la aplicación. 
    File lib\swing-layout-1.0.4.jar ;Copiamos el contenido. 
    ;Store installation folder 
    WriteRegStr HKCU "Software\Tarea_DI07" "" $INSTDIR
    ;Creamos el desinstalador.
    WriteUninstaller "$INSTDIR\Uninstall.exe" ;Definimos la opción de desinstalar.
SectionEnd

;Descripciones que aparecerán junto al componente cuando se seleccionen. Aparece junto al componente.
;Language strings
LangString DESC_SecDummy ${LANG_SPANISH} "Instalacion del fichero Tarea_DI07.jar"
!insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
!insertmacro MUI_DESCRIPTION_TEXT ${SecDummy} $(DESC_SecDummy)
!insertmacro MUI_FUNCTION_DESCRIPTION_END

;Definimos la sección para desinstalar.
Section "un.Desinstalar"
    RMDir /r "$INSTDIR"  ;Borrar todo el directorio de instalación de forma recursiva.
SectionEnd