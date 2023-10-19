<?xml version='1.0' encoding="UTF-8"' ?>
<helpset version="1.0">

  <!-- titulo -->
  <title>Creacion y uso del componente</title>

  <!-- maps -->
  <maps>
     <!-- Página a visualizar por defecto -->
     <homeID>VentanaPrincipal</homeID>

     <!-- Mapa de Fichero  -->
     <mapref location="map_file.jhm"/>
  </maps>
	
  <!-- Las vistas que deseamos mostrar en la ayuda -->
  <view>
    <!-- Nombre de la vista.  En esta caso  -->
    <name>Ayuda de componente</name>
    <!-- Etiqueta asociada a la tabla de contenidos -->
    <label>Tabla de contenidos</label>
    <!-- Clase navegador -->
    <type>javax.help.TOCView</type>
    <!-- El fichero con la tabla de contenidos -->
    <data>toc.xml</data>
  </view>

  <view>
    <name>Indice</name>
    <label>Indice</label>
    <type>javax.help.IndexView</type>
    <data>indice.xml</data>
  </view>

  <view>
    <name>Buscar</name>
    <label>Buscar</label>
    <type>javax.help.SearchView</type>
    <data engine="com.sun.java.help.search.DefaultSearchEngine">
      JavaHelpSearch
    </data>
  </view>

 <presentation default=true>
    <name>Página principal</name>
    <title>Ventana principal de ayuda</title>
    <toolbar>
      <helpaction>javax.help.BackAction</helpaction>
      <helpaction>javax.help.ForwardAction</helpaction>
      <helpaction image="homeicon">javax.help.HomeAction</helpaction>
    </toolbar>
  </presentation>
</helpset>
