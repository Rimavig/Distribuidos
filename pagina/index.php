<?php
$GLOBALS['THRIFT_ROOT'] = 'C:\\Users\\RICHARD\\Downloads\\thrift-0.11.0\\thrift-0.11.0\\lib\\php\\lib\\';

require_once 'Types.php';
require_once 'Servidor.php';

require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/Transport/TTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/Transport/TSocket.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/Protocol/TProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/Protocol/TBinaryProtocol.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/Transport/TBufferedTransport.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/Type/TMessageType.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/Factory/TStringFuncFactory.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/StringFunc/TStringFunc.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/StringFunc/Core.php';
require_once $GLOBALS['THRIFT_ROOT'].'/Thrift/Type/TType.php';
use Thrift\Protocol\TBinaryProtocol;
use Thrift\Transport\TSocket;
use Thrift\Transport\TSocketPool;
use Thrift\Transport\TFramedTransport;
use Thrift\Transport\TBufferedTransport;

$host = '127.0.0.1';
$port = 7911;
$socket = new Thrift\Transport\TSocket($host,$port);
$transport = new TBufferedTransport($socket);
$protocol = new TBinaryProtocol($transport);

$client = new ServidorClient($protocol);
$transport->open();
?>
<html>
<head>
  <title>Top 10</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap-grid.css">
  <link rel="stylesheet" type="text/css" href="css/bootstrap-reboot.css">
  <script src="js/bootstrap.js"></script>
</head>

<body>
  <div class="jumbotron jumbotron-fluid">
    <div class="container">
      <div class="row">
          <div class="col-2">
            <img src="espol.png" width="90%" />
          </div>
        <div class="col-6">
          <h1 class="display-4">Top 10</h1>
          <p class="lead">Lista de las mejores noticias del 2017.
          <br />
          <br />
          <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" >
           <button type="submit" class="btn btn-primary">Generar Top 10</button>
         </form>

          </p>
         </div>
         <div class="col-4">
         <p>
          <b>Integrantes del grupo:</b>
          <ul>
            <li>Richard Vivanco </li>
            <li>Luis Bravo</li>
            <li>Sheyla Cardenas </li>
          </ul>
         </p>

          </div>
      </div>
    </div>
  </div>

  <?php
    $dato1  =$resultado= "";
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
      $re = $client->top10("hola");
      $resultado = "".$re;
    }
   ?>

<br>
<span class="error">
<?php

$valor_array = explode(';',$resultado);
   ?>

<div class="container">
<table class="table table-striped">
    <thead>
      <tr>
        <th>Id</th>
        <th>Visitas</th>
        <th>Titulo</th>
        <th>Editor</th>
      </tr>
    </thead>
    <tbody>
<?php
foreach($valor_array as $llave => $valores) {

    $noticias =explode(',',$valores);
 ?>
    <tr>
    <td> <?php if (isset($noticias[0])) {echo $noticias[0]; }  ?> </td>
    <td> <?php if (isset($noticias[1])) {echo $noticias[1]; }  ?> </td>
    <td> <?php if (isset($noticias[2])) {echo $noticias[2]; }  ?> </td>
    <td> <?php if (isset($noticias[3])) {echo $noticias[3]; }  ?> </td>
    </tr>
    <?php
}
?>
</tbody>
  </table>
  </div>
</span>
<br>
</body>
</html>
