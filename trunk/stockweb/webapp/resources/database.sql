-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema stockweb
--

CREATE DATABASE IF NOT EXISTS stockweb;
USE stockweb;

--
-- Definition of table `chartsetting`
--

DROP TABLE IF EXISTS `chartsetting`;
CREATE TABLE `chartsetting` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `idscript` int(10) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chartsetting`
--

/*!40000 ALTER TABLE `chartsetting` DISABLE KEYS */;
INSERT INTO `chartsetting` (`id`,`idscript`,`name`) VALUES 
 (1,17,'example');
/*!40000 ALTER TABLE `chartsetting` ENABLE KEYS */;


--
-- Definition of table `indicator`
--

DROP TABLE IF EXISTS `indicator`;
CREATE TABLE `indicator` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `descr` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `indicator`
--

/*!40000 ALTER TABLE `indicator` DISABLE KEYS */;
/*!40000 ALTER TABLE `indicator` ENABLE KEYS */;


--
-- Definition of table `scripts`
--

DROP TABLE IF EXISTS `scripts`;
CREATE TABLE `scripts` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `script` text NOT NULL,
  `name` varchar(45) NOT NULL,
  `descr` text,
  `param` varchar(45) NOT NULL,
  `settingchart` varchar(255) default ' ',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scripts`
--

/*!40000 ALTER TABLE `scripts` DISABLE KEYS */;
INSERT INTO `scripts` (`id`,`script`,`name`,`descr`,`param`,`settingchart`) VALUES 
 (17,'function applyScript(candles, json, lastbar, script, input) {\n\n    json.put(\"high\", candles.High(lastbar));\n    json.put(\"low\", candles.Low(lastbar));\n    json.put(\"close\", candles.Close(lastbar));\n    json.put(\"open\", candles.Open(lastbar));\n\n    input.put(\"ncandles\",\"7\");\n    var rs = script.call(\"$getVolumeMedio\", lastbar, input);\n    println(script.get(rs,\"return\"));\n\n    //println(map.get(\'scriptname\'));\n    return json;\n}\n','script 01','xxx','high,low,close,open','#36B0C8,#FE0101,histograma,0,high|#F91111,#FE014D,line,1,low|#0144FB,#FE014D,line,1,close|#09FF01,#FE014D,line,1,open'),
 (18,'function applyScript(candles, json) {\n    json.put(\"result4\", \"1\");\n    json.put(\"result5\", \"2\");\n    json.put(\"result6\", \"3\");\n\n    return json;\n}\n','script 02','yyy','result4,result5,result6','#017FFE,#FE0101,line,0,result4|#017FFE,#FE014D,histograma,1,result5|#017FFE,#FE014D,line,1,result6|undefined|undefined|undefined'),
 (19,'function applyScript(candle, json, lastbar, script, input) {\n    // $getCandleSize(candle, tipo, sombra)\n    // TIPO = Corpo (1) , Sombra(2) ou Inteiro Candle(3) do Candle\n    // Sombra = Superior (1) ou Inferior (2)\n    var result = 0;\n\n    var tipo = eval(input.get(\'tipo\'));\n    var sombra = eval(input.get(\'sombra\'));\n   \n//    println(\"tipo: \" + tipo);\n//    println(\"sombra: \" + sombra);\n\n    var SinalCandle = (candle.Close(lastbar) > candle.Open(lastbar));\n    if (tipo == 1) {\n        if (SinalCandle) {\n            result = Math.abs(((candle.Close(lastbar) / candle.Open(lastbar)) * 100) - 100);\n        } else {\n            result = Math.abs(((candle.Open(lastbar) / candle.Close(lastbar)) * 100) - 100);\n        }\n\n    } else if (tipo == 2) {\n\n        if (SinalCandle) {\n            if (sombra == 1) {\n                result = Math.abs(((candle.High(lastbar) / candle.Close(lastbar)) * 100) - 100);\n            } else if (sombra == 2) {\n                result = Math.abs(((candle.Open(lastbar) / candle.Low(lastbar)) * 100) - 100);\n            }\n        } else { if (sombra == 1) {\n                result = Math.abs(((candle.High(lastbar) / candle.Open(lastbar)) * 100) - 100);\n            } else if (sombra == 2) {\n                result = Math.abs(((candle.Close(lastbar) / candle.Low(lastbar)) * 100) - 100);\n            }\n        }\n\n        if (tipo == 3) {\n            result = Math.abs(((candle.High(lastbar) / candle.Low(lastbar)) * 100) - 100);\n        }\n        \n        \n    }\n    json.put(\"return\", result);\n    return json;\n}\n','candleSize','$getCandleSize(position, tipo, sombra)','return',''),
 (21,'function applyScript(candle, json, lastbar, script, input) {\n\n    //$getTendencia(begin, end)\n    var result = 0;\n    var begin = input.get(\"begin\");\n    var end = input.get(\"end\");\n\n    var QtdeTendenciaBaixa = 0;\n    var QtdeTendenciaAlta = 0;\n\n    for (var x = begin; x <= (end-1); x++) {\n        var resultTendenciaCandle = 1; //script.call(\"tendenciaAltaBaixa\",x,input);\n        if (resultTendenciaCandle == 0) {\n            QtdeTendenciaBaixa = QtdeTendenciaBaixa + 1;\n        }\n\n        if (resultTendenciaCandle == 1) {\n            QtdeTendenciaAlta = QtdeTendenciaAlta + 1;\n        }\n\n    }\n\n    var ExistTendencia = (QtdeTendenciaBaixa != 0) && (QtdeTendenciaAlta != 0);\n\n    if (ExistTendencia = 1) {\n        result = -1;\n    } else if (QtdeTendenciaBaixa == 0) { // QtdeTendenciaBaixa = 0 significa que a tendencia é de ALTA\n        result = 1;\n    } else if (QtdeTendenciaAlta == 0) { // QtdeTendenciaAlta = 0 significa que a tendencia é de BAIXA\n        result = 0;\n    }\n\n    json.put(\"return\", result);\n    return json;\n}\n','$getTendencia','//$getTendencia(begin, end)','return',''),
 (22,'function applyScript(candle, json, lastbar, script, input) {\n\n    // $getCorpoCandle(lastbar)\n    var CorpoCandle = 0;\n\n    if (candle.Close(lastbar) > candle.Open(lastbar)) {\n        CorpoCandle = Math.abs(((candle.Open(lastbar) / candle.Close(lastbar)) * 100) - 100)\n    } else {\n        //CorpoCandle = ABS(((Close[candle] / Open[candle])*100)-100)\n        CorpoCandle = Math.abs(((candle.High(lastbar) / candle.Close(lastbar)) * 100) - 100)\n    }\n\n    json.put(\"return\", CorpoCandle)\n    return json\n}\n','$getCorpoCandle','$getCorpoCandle(lastbar)','return',''),
 (23,'function applyScript(candle, json, lastbar, script, input) {\n\n    var SinalCandle1 = false;\n    var SinalCandle2 = false;\n    var TendenciaCandleAlta = false;\n    var TendenciaCandleBaixa = false;\n    var QtdeTendenciaBaixa = 0;\n    var QtdeTendenciaAlta = 0;\n    var result = 0;\n\n    var x = lastbar;\n\n    var SinalCandle1 = (candle.Close(x) > candle.Open(x));\n    var SinalCandle2 = (candle.Close(x + 1) > candle.Open(x + 1));\n\n    // Verificando Tendencia de Baixa dos Candles\n    if (SinalCandle1 && SinalCandle2) {\n        TendenciaCandleAlta = candle.Close(x) > candle.Close(x + 1);\n        TendenciaCandleBaixa = candle.Close(x) < candle.Close(x + 1);\n    } else if ((SinalCandle1) && (SinalCandle2 == false)) {\n        TendenciaCandleAlta = (candle.Close(x) > candle.Open(x + 1)) || (candle.Close(x) > candle.Close(x + 1));\n        TendenciaCandleBaixa = (candle.Close(x) < candle.Open(x + 1)) && (candle.Close(x) < candle.Close(x + 1));\n    } else if ((SinalCandle1 == false) && (SinalCandle2)) {\n        TendenciaCandleAlta = (candle.Open(x) > candle.Close(x + 1)) && (candle.Close(x) > candle.Open(x + 1));\n        TendenciaCandleBaixa = (candle.Open(x) < candle.Close(x + 1)) || (candle.Close(x) < candle.Open(x + 1));\n    } else if ((SinalCandle1 == false) && (SinalCandle2 == false)) {\n        TendenciaCandleAlta = (candle.Open(x) > candle.Open(x + 1)) && (candle.Close(x) > candle.Open(x + 1));\n        TendenciaCandleBaixa = (candle.Open(x) < candle.Open(x + 1)) || (candle.Close(x) < candle.Close(x + 1));\n    }\n\n    if (TendenciaCandleBaixa) {\n        QtdeTendenciaBaixa = QtdeTendenciaBaixa + 1;\n    }\n\n    if (TendenciaCandleAlta) {\n        QtdeTendenciaAlta = QtdeTendenciaAlta + 1;\n    }\n\n    if (QtdeTendenciaBaixa == 0) { // QtdeTendenciaBaixa = 0 significa que a tendencia é de ALTA\n        result = 1;\n    } else if (QtdeTendenciaAlta = 0) { // QtdeTendenciaAlta = 0 significa que a tendencia é de BAIXA\n        result = 0;\n    }\n    json.put(\"return\", result);\n    return json;\n\n}\n','$getTendenciaAltaBaixa','$getTendenciaAltaBaixa(candleposition)','return',''),
 (24,'function applyScript(candle, json, lastbar, script, input) {\n\n    //$getVolumeMedio(ncandles)\n    var ncandles = input.get(\"ncandles\");\n    var MediaVolume = 0;\n\n    for (var x=lastbar; x>(lastbar - ncandles); x--) {\n        MediaVolume += candle.Volume(x);\n    }\n    println(\"MediaVolume : \" + MediaVolume + \"/\" + ncandles);\n    var Media = eval(MediaVolume / ncandles);\n    //MediaMin = ((MediaVolume / ncandles) / 2.5)*2\n    json.put(\"return\", Media);\n    return json; //, MediaMin AS \"Media Minima\"\n}\n','$getVolumeMedio','$getVolumeMedio(ncandles)','return','');
/*!40000 ALTER TABLE `scripts` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
