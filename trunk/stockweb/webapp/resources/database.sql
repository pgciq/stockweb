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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scripts`
--

/*!40000 ALTER TABLE `scripts` DISABLE KEYS */;
INSERT INTO `scripts` (`id`,`script`,`name`,`descr`,`param`,`settingchart`) VALUES 
 (17,'function applyScript(candles, json, position, script, input) {\n    var lastbar = parseInt(position);\n\n/*\n    println(\"tendencia: \" + candles.getTendencia(lastbar, lastbar+3));\n    println(\"begin: \" + lastbar);\n    println(\"end: \" + (lastbar+1));\n    println(\"date: \" + candles.Date(lastbar));\n\n\n    json.put(\"high\", candles.High(lastbar));\n    json.put(\"low\", candles.Low(lastbar));\n    json.put(\"close\", candles.Close(lastbar));\n    json.put(\"open\", candles.Open(lastbar));\n\n//    input.put(\"candles\",\"2\");\n\n    var rs = script.call(\"$getTendenciaAltaBaixa\", lastbar, input);\n    println(script.get(rs,\"return\"));\n    json.put(\"result\",candles.getTendencia(lastbar,lastbar+2));\n    json.put(\"result\",candles.isHangingManORHammer(lastbar));\n    var rs = script.call(\"Bullish Tri-Star\", lastbar, input);\n*/\n\n    var rs = script.call(\"Bearish Two Crows\", lastbar, input);\n    println(script.get(rs,\"result\"));\n    json.put(\"result\", script.get(rs,\"result\"));\n\n    return json;\n}\n','script 01','xxx','result','#36B0C8,#FE0101,histograma,0,high'),
 (23,'function applyScript(candle, json, lastbar, script, input) {\n\n    var SinalCandle1 = false;\n    var SinalCandle2 = false;\n    var TendenciaCandleAlta = false;\n    var TendenciaCandleBaixa = false;\n    var QtdeTendenciaBaixa = 0;\n    var QtdeTendenciaAlta = 0;\n    var result = 0;\n\n    var x = lastbar;\n\n    var SinalCandle1 = (candle.Close(x) > candle.Open(x));\n    var SinalCandle2 = (candle.Close(x + 1) > candle.Open(x + 1));\n\n    // Verificando Tendencia de Baixa dos Candles\n    if (SinalCandle1 && SinalCandle2) {\n        TendenciaCandleAlta = candle.Close(x) > candle.Close(x + 1);\n        TendenciaCandleBaixa = candle.Close(x) < candle.Close(x + 1);\n    } else if ((SinalCandle1) && (SinalCandle2 == false)) {\n        TendenciaCandleAlta = (candle.Close(x) > candle.Open(x + 1)) || (candle.Close(x) > candle.Close(x + 1));\n        TendenciaCandleBaixa = (candle.Close(x) < candle.Open(x + 1)) && (candle.Close(x) < candle.Close(x + 1));\n    } else if ((SinalCandle1 == false) && (SinalCandle2)) {\n        TendenciaCandleAlta = (candle.Open(x) > candle.Close(x + 1)) && (candle.Close(x) > candle.Open(x + 1));\n        TendenciaCandleBaixa = (candle.Open(x) < candle.Close(x + 1)) || (candle.Close(x) < candle.Open(x + 1));\n    } else if ((SinalCandle1 == false) && (SinalCandle2 == false)) {\n        TendenciaCandleAlta = (candle.Open(x) > candle.Open(x + 1)) && (candle.Close(x) > candle.Open(x + 1));\n        TendenciaCandleBaixa = (candle.Open(x) < candle.Open(x + 1)) || (candle.Close(x) < candle.Close(x + 1));\n    }\n\n    if (TendenciaCandleBaixa) {\n        QtdeTendenciaBaixa = QtdeTendenciaBaixa + 1;\n    }\n\n    if (TendenciaCandleAlta) {\n        QtdeTendenciaAlta = QtdeTendenciaAlta + 1;\n    }\n\n    if (QtdeTendenciaBaixa == 0) { // QtdeTendenciaBaixa = 0 significa que a tendencia é de ALTA\n        result = 1;\n    } else if (QtdeTendenciaAlta = 0) { // QtdeTendenciaAlta = 0 significa que a tendencia é de BAIXA\n        result = 0;\n    }\n    json.put(\"return\", result);\n    return json;\n\n}\n','$getTendenciaAltaBaixa','$getTendenciaAltaBaixa(candleposition)','return',''),
 (24,'function applyScript(candle, json, lastbar, script, input) {\n\n    //$getVolumeMedio(ncandles)\n    var ncandles = input.get(\"ncandles\");\n    var MediaVolume = 0;\n\n    for (var x=lastbar; x>(lastbar - ncandles); x--) {\n        MediaVolume += candle.Volume(x);\n    }\n    println(\"MediaVolume : \" + MediaVolume + \"/\" + ncandles);\n    var Media = eval(MediaVolume / ncandles);\n    var MediaMin = eval(((MediaVolume / ncandles) / 2.5)*2);\n\n    json.put(\"media\", Media);\n    json.put(\"mediamin\", MediaMin);\n\n    return json;\n}\n','$getVolumeMedio','$getVolumeMedio(ncandles)','media,mediamin',''),
 (30,'function applyScript(candles, json, lastbar, script, input) {\n\n    result = 0;\n\n    var CorpoCandle1 = candles.getCandleSize(lastbar, 1, 0);\n    var SombraSup1 = candles.getCandleSize(lastbar, 2, 1);\n    var SombraInf1 = candles.getCandleSize(lastbar, 2, 2);\n    var LargCandle1 = CorpoCandle1 + SombraSup1 + SombraInf1;\n\n    var CorpoCandle2 = candles.getCandleSize((lastbar + 1), 1, 0);\n    var SombraSup2 = candles.getCandleSize((lastbar + 1), 2, 1);\n    var SombraInf2 = candles.getCandleSize((lastbar + 1), 2, 2);\n    var LargCandle2 = CorpoCandle2 + SombraSup2 + SombraInf2;\n\n    var CorpoCandle3 = candles.getCandleSize((lastbar + 2), 1, 0);\n    var SombraSup3 = candles.getCandleSize((lastbar + 2), 2, 1);\n    var SombraInf3 = candles.getCandleSize((lastbar + 2), 2, 2);\n    var LargCandle3 = CorpoCandle3 + SombraSup3 + SombraInf3;\n    //=================================================================================\n    // 3 corvos idênticos (Bearish Identical 3 Crows)\n    var x = 1.5;\n\n    DojiCandle1 = candles.Doji(lastbar);\n    DojiCandle2 = candles.Doji(lastbar + 1);\n    DojiCandle3 = candles.Doji(lastbar + 1);\n\n    TendenciaUltimosCandles = candles.getTendencia(lastbar + 2, lastbar + 3);\n\n    TerceiroCandleBaixa = DojiCandle3 && ((candles.Open(lastbar + 2) < candles.Close(3)) || (candles.Open(lastbar + 2) < candles.Open(3))) && ((candles.Close(lastbar + 2) < candles.Close(3)) || (candles.Close(lastbar + 2) < candles.Open(3))) && ((LargCandle1 < LargCandle2 * x) && (LargCandle1 < LargCandle3 * x));\n\n    SegundoCandleBaixa = DojiCandle2 && ((candles.Open(lastbar + 1) < candles.Close(lastbar + 2)) || (candles.Open(lastbar + 1) < candles.Open(lastbar + 2))) && ((candles.Close(lastbar + 1) < candles.Close(lastbar + 2)) || (candles.Close(lastbar + 1) < candles.Open(lastbar + 2))) && ((LargCandle2 < LargCandle1 * x) && (LargCandle2 < LargCandle3 * x));\n\n    PrimeiroCandleBaixa = DojiCandle1 && ((candles.Open(lastbar) > candles.Close(lastbar + 2)) || (candles.Open(lastbar) > candles.Open(lastbar + 2))) && ((candles.Close(lastbar) > candles.Close(lastbar + 2)) || (candles.Close(lastbar) > candles.Open(lastbar + 2))) && ((LargCandle3 < LargCandle1 * x) && (LargCandle3 < LargCandle2 * x));\n\n    //perc60Candle = (((Open[1] - Close[1]) / 100) * 50) //Retorna o valor do preço que representa 10%\n    //perc40Candle = (((Open[1] - Close[1]) / 100) * 40) //Retorna o valor do preço que representa 10%\n    TresEstrelas = TerceiroCandleBaixa && SegundoCandleBaixa && PrimeiroCandleBaixa && (TendenciaUltimosCandles = 1);\n\n    if (TresEstrelas) {\n        result = -19;\n    }\n\n    json.put(\"result\", result);\n    return json;\n}\n','Bullish Tri-Star','3 estrelas (Bullish Tri-Star)\n--------------------------------\nDescrição : Todos os 3 dias são dojis.\nSegundo dia abre com gap de baixa abaixo do primeiro dia e do terceiro\n\nPsicologia\n\nPadrão raro, então esteja sempre atento com essa formação. Este padrão não é válido para ações com baixo volume. A grande indecisão criada por esses três dojis deve ser ignorada por traders. Este nível de indecisão emite fortes sugestões que a tendência está para mudar. E é sinal de reversão para alta.\n\nTipo : BULLISH/ALTA REVERSÃO','result','#000000,#000000,Histograma,0'),
 (31,'function applyScript(candles, json, lastbar, script, input) {\n\n    var result = 0;\n\n    var CorpoCandle1 = candles.getCandleSize(lastbar, 1, 0);\n    var SombraSup1 = candles.getCandleSize(lastbar, 2, 1);\n    var SombraInf1 = candles.getCandleSize(lastbar, 2, 2);\n\n    var CorpoCandle2 = candles.getCandleSize(lastbar + 1, 1, 0);\n    var SombraSup2 = candles.getCandleSize(lastbar + 1, 2, 1);\n    var SombraInf2 = candles.getCandleSize(lastbar + 1, 2, 2);\n\n    var CorpoCandle3 = candles.getCandleSize(lastbar + 2, 1, 0);\n    var SombraSup3 = candles.getCandleSize(lastbar + 2, 2, 1);\n    var SombraInf3 = candles.getCandleSize(lastbar + 2, 2, 2);\n\n    var CorpoCandle4 = candles.getCandleSize(lastbar + 3, 1, 0);\n    var SombraSup4 = candles.getCandleSize(lastbar + 3, 2, 1);\n    var SombraInf4 = candles.getCandleSize(lastbar + 3, 2, 2);\n\n    var CorpoCandle5 = candles.getCandleSize(lastbar + 4, 1, 0);\n    var SombraSup5 = candles.getCandleSize(lastbar + 4, 2, 1);\n    var SombraInf5 = candles.getCandleSize(lastbar + 4, 2, 2);\n\n    var LarguraCandle5 = (CorpoCandle5 + SombraSup5 + SombraInf5);\n    var LarguraCandle4 = (CorpoCandle4 + SombraSup4 + SombraInf4);\n    var LarguraCandle3 = (CorpoCandle3 + SombraSup3 + SombraInf3);\n    var LarguraCandle2 = (CorpoCandle2 + SombraSup2 + SombraInf2);\n    var LarguraCandle1 = (CorpoCandle1 + SombraSup1 + SombraInf1);\n\n    var TendenciaUltimosCandles = candles.getTendencia(5, 6);\n\n    var QuintoCandleBaixa = (candles.Open(lastbar + 4) > candles.Close(lastbar + 4)) && (CorpoCandle5 > (SombraSup5 + SombraInf5));\n\n    var QuartoCandle = ((candles.Open(lastbar + 3) > candles.Close(lastbar + 4)) || (candles.Open(lastbar + 3) > candles.Close(lastbar))) && (CorpoCandle4 > (SombraSup4 + SombraInf4)) && (LarguraCandle4 * 2 < LarguraCandle5);\n\n    var TerceiroCandle = ((candles.Open(lastbar + 2) > candles.Close(lastbar + 4)) || (candles.Open(lastbar + 2) > candles.Close(lastbar))) && (CorpoCandle3 > (SombraSup3 + SombraInf3)) && (LarguraCandle3 * 2 < LarguraCandle5);\n\n    var SegundoCandle = ((candles.Open(lastbar + 1) > candles.Close(lastbar + 4)) || (candles.Open(lastbar + 1) > candles.Close(lastbar))) && (CorpoCandle2 > (SombraSup2 + SombraInf2)) && (LarguraCandle2 * 2 < LarguraCandle5);\n\n    var PrimeiroCandleBaixa = (candles.Open(lastbar) > candles.Close(lastbar)) && (CorpoCandle1 > (SombraSup1 + SombraInf1)) && (candles.Close(lastbar) < candles.Low(lastbar + 4)) && ((LarguraCandle1 > LarguraCandle2) && (LarguraCandle1 > LarguraCandle3) && (LarguraCandle1 > LarguraCandle4));\n\n    var TresMetodosQueda = (TendenciaUltimosCandles == 0) && QuintoCandleBaixa && QuartoCandle && TerceiroCandle && SegundoCandle && PrimeiroCandleBaixa;\n\n    if (TresMetodosQueda) {\n        result = 19;\n    }\n\n    json.put(\"result\", result);\n    return json;\n}\n','Bearish 3 methods','','result',''),
 (32,'function applyScript(candles, json, lastbar, script, input) {\n\n    var result = 0;\n\n    //=================================================================================\n    // 2 corvos (Bearish 2 Crows)\n    var CorpoCandle1 = candles.getCandleSize(lastbar, 1, 0);\n    var SombraSup1 = candles.getCandleSize(lastbar, 2, 1);\n    var SombraInf1 = candles.getCandleSize(lastbar, 2, 2);\n\n    var CorpoCandle3 = candles.getCandleSize(lastbar+2, 1, 0);\n    var SombraSup3 = candles.getCandleSize(lastbar+2, 2, 1);\n    var SombraInf3 = candles.getCandleSize(lastbar+2, 2, 2);\n    var TendenciaUltimosCandles = candles.getTendencia(lastbar+2, lastbar+4);\n\n    var TerceiroCandleAlta = (candles.Open(lastbar + 2) < candles.Close(lastbar + 2)) && (CorpoCandle3 > (SombraSup3 + SombraInf3));\n\n    var SegundoCandleBaixa = ((candles.Open(lastbar + 1) > candles.Close(lastbar + 2)) && (candles.Close(lastbar + 1) > candles.Close(lastbar + 2))) && (candles.Open(lastbar+1) != candles.Close(lastbar+1)) && (candles.Close(lastbar + 1) < candles.Open(lastbar+1)) && (candles.High(lastbar + 1) > candles.High(lastbar + 2)) && (candles.Low(lastbar + 1) > candles.High(lastbar+2));\n\n    //perc60Candle = (((Open[1] - Close[1]) / 100) * 50) //Retorna o valor do preço que representa 10%\n    //perc40Candle = (((Open[1] - Close[1]) / 100) * 40) //Retorna o valor do preço que representa 10%\n\n    var PrimeiroCandleBaixa = (candles.Open(lastbar) > candles.Close(lastbar)) && (candles.Open(lastbar) <= candles.Open(lastbar + 1)) && (candles.Open(lastbar) > candles.Close(lastbar + 1)) && (CorpoCandle1 > (SombraSup1 + SombraInf1));\n\n    var DoisCorvos = TerceiroCandleAlta && SegundoCandleBaixa && PrimeiroCandleBaixa && (TendenciaUltimosCandles == 1);\n\n    if (DoisCorvos) {\n        result = -18;\n    }\n    json.put(\"result\", result);\n    return json;\n}','Bearish Two Crows','Bearish 2 Crows (2 corvos de baixa) \n\nDescrição:\nNo primeiro dia forma-se um longo candle branco continuando a tendência de alta. No segundo dia ocorre um gap com um candle de baixa. No terceiro dia ocorre um candle de baixa engolfando o candle anterior, fechando acima do fechamento do primeiro dia.\n\nInterpretação:\nApós formado o gap, o segundo candle mostra que o mercado perdeu um pouco de força. O terceiro candle que envolve o segundo apresenta um sinal de mais perda de força do movimento, significando que o mercado não segurará mais essa posição, pronto para a formação de um topo e uma possível reversão. ','result','');
/*!40000 ALTER TABLE `scripts` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
