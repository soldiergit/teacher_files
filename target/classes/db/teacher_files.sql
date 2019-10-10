-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: teacher_files
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.19.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `academic_paper`
--

DROP TABLE IF EXISTS `academic_paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_paper` (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `paper_name` varchar(255) DEFAULT NULL,
  `paper_type` int(11) DEFAULT NULL,
  `paper_title` varchar(255) DEFAULT NULL,
  `teacher_type` int(11) DEFAULT NULL,
  `sign_unit` int(11) DEFAULT NULL,
  `periodical_name` varchar(255) DEFAULT NULL,
  `periodical_number` varchar(255) DEFAULT NULL,
  `publish_time` date DEFAULT NULL,
  `item_member` varchar(255) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `paper_grade_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`paper_id`),
  KEY `FK1sjhyo03rhqqnwrj9h9o29cvn` (`paper_grade_id`),
  KEY `FK5sqfeufl9788mchnf2owsuyf3` (`teacher_id`),
  CONSTRAINT `FK1sjhyo03rhqqnwrj9h9o29cvn` FOREIGN KEY (`paper_grade_id`) REFERENCES `academic_paper_grade` (`id`),
  CONSTRAINT `FK5sqfeufl9788mchnf2owsuyf3` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='学术论文';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_paper`
--

LOCK TABLES `academic_paper` WRITE;
/*!40000 ALTER TABLE `academic_paper` DISABLE KEYS */;
INSERT INTO `academic_paper` VALUES (1,'Effective kalman filtering algorithm for distributed multichannel speech enhancement',2,'暂无',1,1,'Neurocomputing','Neurocomputing, 2018, 275: 144-154','2018-01-01','18',NULL,18,3),(2,'Inferring Gene Regulatory Networks Based on a Hybrid Parallel Genetic Algorithm and the Threshold Restriction Method',2,'暂无',1,2,'Interdisciplinary Sciences Computational Life Sciences','1913-2751','2018-03-01','56',NULL,56,5),(3,'Cloud computing service center model for medical service and data processing',2,'暂无',1,1,'CPCI-S检索会议论文（2nd International Conference on Automation,Mechanical Control and Computational Engineering)','ISSN 1951-6851','2017-01-01','1',NULL,1,4),(4,'Research on feature classification method of network text data based on association rules',2,'暂无',2,1,'International Journal of Computers and Applications','暂无','2018-06-10','48',NULL,48,6),(5,'时间尺度上二阶Emden-Fowler型延迟动态方程的振动性',2,'暂无',2,1,'振动与冲击,2018,37(16)(EI论文JA类)','ISSN:1000-3835,CN: 31-1316/TU','2018-08-01','13',NULL,13,6),(6,'Inferring genome-wide gene regulatory networks with GPU or CPU parallel algorithm',2,'暂无',1,1,'2017 International Conference on Computer Network, Electronic and Automation (ICCNEA 2017)','2470-8038','2017-12-01','56',NULL,56,7),(7,'Research on key technology and application of big data integrated machine',2,'暂无',1,1,'7th International Conference on Applications and Techniques in Cyber Intelligence (ICATCI 2018)','2194-5357','2018-07-11','56',NULL,56,7),(8,'Differential expression analysis based on expression data of multiple platforms',2,'暂无',1,1,'7th International Conference on Applications and Techniques in Cyber Intelligence (ICATCI 2018)','2194-5357','2018-07-12','56',NULL,56,7),(9,'A Data Storage and Mining Algorithm based on Distributed File System',2,'暂无',2,1,'International Conference on Applications and Techniques in Cyber Intelligence 2018','ISSN 2194-5357','2018-01-01','51',NULL,51,7),(10,'Cloud Service Discovery and Key Matching Methods in the Semantic Internet of Things',2,'暂无',1,1,'International Conference on Applications and Techniques in Cyber Intelligence 2018','ISSN 2194-5357','2018-01-01','51',NULL,51,7),(11,'One novel representation of DNA sequence based on the global and local position information',2,'暂无',1,1,'SCIENTIfIC REPORTS（SCI 3区） ','ISSN 2045-2322','2018-01-01','1',NULL,1,7),(12,'An Information hiding method based on conditional selection statement execution paths',2,'暂无',1,1,'2018 5th International Conference on Information Science and Control Engineering','暂无','2018-01-01','52',NULL,52,7),(13,'时间模上一类二阶非线性延迟动力系统的振动性分析',2,'暂无',2,1,'应用数学学报,2018,41(3)(梧州学院A类)','ISSN:0254-3079,CN11-2040/O1','2018-05-01','13',NULL,13,8),(14,'时间测度链上二阶Emden-Fowler型动态方程的振动性',2,'暂无',1,1,'吉林大学学报(理学版), 2018, 56(1)(梧州学院B级)','ISSN:1671-5489,CN:22-1340/O','2018-01-01','13',NULL,13,9),(15,'具阻尼项的二阶Emden-Fowler型泛函差分方程的振动性',2,'暂无',1,1,'华中师范大学学报(自然科学版), 2017,51(6)(梧州学院B级)','ISSN:1000-1190, 42-1178/N','2017-12-01','13',NULL,13,9),(16,'HEVC视频标准的激光图像编码与优化',2,'暂无',1,1,'激光杂志','ISSN：0253-2743；CN：50-1085/TN','2018-08-01','24',NULL,24,9),(17,'遥感多视点图像中心子像素定位方法仿真研究',2,'暂无',2,1,'计算机仿真','暂无','2018-03-01','22',NULL,22,9),(18,'时间测度链上动力方程振动性的进展',2,'暂无',2,1,'安徽大学学报(自然科学版),2018,42(1)(梧州学院C级)','ISSN:1000-2162,CN34-1063/N','2018-01-01','13',NULL,13,10),(19,'时间模上一类二阶阻尼Emden-Fowler型动态方程的振荡性',2,'暂无',1,1,'数学物理学报, 2018, 38A(1)(梧州学院C级)','ISSN:1003-3998,CN42-1226/O','2018-02-01','13',NULL,13,10),(20,'一类具正负系数的高阶泛函差分方程的振荡性',2,'暂无',1,1,'数学的实践与认识,　2018,48(6)(梧州学院C级)','ISSN:1000-0984,CN11-2018/O1','2018-03-01','13',NULL,13,10),(21,'低码率分形视频图像分层压缩方法仿真',2,'暂无',1,1,'计算机仿真','ISSN：1006-9348；CN：11-3724/TP','2018-07-01','24',NULL,24,10),(22,'一种单幅运动模糊图像的车速测量算法',2,'暂无',1,1,'中国测试','ISSN：1674-5124','2019-07-25','43',NULL,43,10),(23,'一类具有Hilbert核的奇异积分方程的三角Hermite插值小波方法',2,'暂无',1,1,'山西大学学报','ISSN 0253-2395','2019-07-25','7',NULL,7,10),(24,'遥感地理图像采集目标精准提取仿真',2,'暂无',1,1,'计算机仿真','ISSN10069348','2018-08-01','25',NULL,25,10),(25,'一种基于转发区域的三维地理位置路由协议',2,'暂无',1,1,'现代电子技术','1004-373X','2018-01-01','53',NULL,53,10),(26,'注意力经济对高校课堂教学的启发',1,'暂无',3,3,'梧州学院学报','无','2018-01-01','52',NULL,52,11),(27,'以培养独立思考能力的课程改革探索——以《计算机文化基础》课程为例',1,'暂无',3,3,'教育教学论坛','无','2016-01-01','41',NULL,41,11),(28,'指纹在高校考生身份识别中的应用研究',1,'暂无',3,3,'数学技术与应用','无','2017-01-01','43',NULL,43,11),(29,'TPACK带给高校教师教学改革的启发——以梧州学院“计算机文化基础”课程教学为例',1,'暂无',3,3,'梧州学院学报','无','2016-01-01','43',NULL,43,11),(30,'教育信息化发展带给高校教师的挑战及应对建议',1,'暂无',3,3,'亚太教育','无','2016-01-01','43',NULL,43,11),(31,'工科“中职生本”人才培养模式探讨',1,'暂无',3,3,'教育教学论坛','无','2019-01-01','1',NULL,1,11),(33,'Dynamical Properties of Strongly Descendible Map on N Dimensional Monomer',2,'暂无',1,2,'2018 10th international conference on measuring technology and mechatronics automation  ','无','2018-01-01','61',NULL,61,7),(34,'Various Shadowing Property of Product Map',2,'暂无',1,1,'2018 10th international conference on measuring technology and mechatronics automation  ','无','2018-01-01','61',NULL,61,7),(35,'Point Sets and Periodic Shadowing Property of Topological G-Conjugacy on Metric G-Space',2,'暂无',1,1,'2018 10th international conference on measuring technology and mechatronics automation  ','无','2018-01-01','61',NULL,61,7),(36,'度量G-空间中G-链等价集的动力学性质',2,'暂无',1,1,'华中师范大学学报：自然科学版','42-1178/N','2018-08-01','61',NULL,61,9),(37,'拓扑群作用下度量空间中链回归点集的研究',2,'暂无',1,1,'数学的实践与认识','11-2018/O1','2018-01-01','61',NULL,61,10),(38,'度量G-空间中强一致收敛条件下混合性的研究',2,'暂无',1,1,'数学的实践与认识','11-2018/O1','2018-06-01','61',NULL,61,10);
/*!40000 ALTER TABLE `academic_paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `academic_paper_annex`
--

DROP TABLE IF EXISTS `academic_paper_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_paper_annex` (
  `paper_annex_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `paper_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`paper_annex_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='论文附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_paper_annex`
--

LOCK TABLES `academic_paper_annex` WRITE;
/*!40000 ALTER TABLE `academic_paper_annex` DISABLE KEYS */;
/*!40000 ALTER TABLE `academic_paper_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `academic_paper_grade`
--

DROP TABLE IF EXISTS `academic_paper_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_paper_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='论文等级';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_paper_grade`
--

LOCK TABLES `academic_paper_grade` WRITE;
/*!40000 ALTER TABLE `academic_paper_grade` DISABLE KEYS */;
INSERT INTO `academic_paper_grade` VALUES (1,'国家级'),(2,'省级'),(3,'SCI2区'),(4,'SCI3区'),(5,'SCI4区'),(6,'EI检索期刊论文JA类型'),(7,'ISTP、EI检索会议论文CA类型'),(8,'梧州学院核心A级'),(9,'梧州学院核心B级'),(10,'梧州学院核心C级'),(11,'其它');
/*!40000 ALTER TABLE `academic_paper_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  `prize_time` date DEFAULT NULL,
  `prize_img` varchar(255) DEFAULT NULL,
  `dept_id` varchar(255) DEFAULT NULL,
  `dept_name` varchar(255) DEFAULT NULL,
  `awardee` varchar(255) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `prize_level_id` int(11) DEFAULT NULL,
  `prize_grade_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FKn70xtf8crnb18k56rn59g309g` (`prize_grade_id`),
  KEY `FKi0a9r7x0qpmpxk820ewvord6r` (`prize_level_id`),
  KEY `FKne1gn9du4mvxnp7ckofdu965h` (`teacher_id`),
  KEY `FKn38nn82aimqbwwbkks5gunm6s` (`match_id`),
  CONSTRAINT `FKi0a9r7x0qpmpxk820ewvord6r` FOREIGN KEY (`prize_level_id`) REFERENCES `competition_prize_level` (`id`),
  CONSTRAINT `FKn38nn82aimqbwwbkks5gunm6s` FOREIGN KEY (`match_id`) REFERENCES `sys_match` (`match_id`),
  CONSTRAINT `FKn70xtf8crnb18k56rn59g309g` FOREIGN KEY (`prize_grade_id`) REFERENCES `competition_prize_grade` (`id`),
  CONSTRAINT `FKne1gn9du4mvxnp7ckofdu965h` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='学生竞赛项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
INSERT INTO `competition` VALUES (1,'“绿”动大学','2016-01-01',NULL,'1','大数据与软件工程学院','卢秋树、容传毅、黄伟印',1,22,1,2),(2,'5 GreenS','2016-01-01',NULL,'1','大数据与软件工程学院','吴枝晏',1,25,1,2),(3,'《丢》','2016-01-01',NULL,'1','大数据与软件工程学院','廖治欢',1,22,1,2),(4,'一念之差，一步之遥','2016-01-01',NULL,'1','大数据与软件工程学院','覃宁、杨戴菲',1,23,1,3),(5,'周末玩乜嘢','2016-01-01',NULL,'1','大数据与软件工程学院','潘恒飞、卢善坚、唐高杰',1,4,1,3),(6,'“绿”动大学','2016-01-01',NULL,'1','大数据与软件工程学院','卢秋树、容传毅、黄伟印',1,22,2,1),(7,'5 GreenS','2016-01-01',NULL,'1','大数据与软件工程学院','吴枝晏',1,25,2,1),(8,'《丢》','2016-01-01',NULL,'1','大数据与软件工程学院','廖治欢',1,22,2,1),(9,'留','2016-01-01',NULL,'1','大数据与软件工程学院','廖治欢',1,24,2,3),(10,'华夏之裳','2016-01-01',NULL,'1','大数据与软件工程学院','钟琦、王安琪、吴丽安、姚潇婷',1,28,2,3),(11,'等级官帽表情包','2016-01-01',NULL,'1','大数据与软件工程学院','覃燕梅、何丽梅',1,27,2,3),(12,'世界文化遗产之怀远楼','2016-01-01',NULL,'1','大数据与软件工程学院','陈鑫奇、陈超、易鹏先、庞威',1,23,2,3),(13,'梧（吾）镇六堡——一叶品茗','2016-01-01',NULL,'1','大数据与软件工程学院','陆烽、阳文静',1,25,2,3),(14,'还世界一片绿色','2016-01-01',NULL,'1','大数据与软件工程学院','钟珊珊、梁博秋',1,23,2,3),(15,'给贫困儿童一个绿色世界','2016-01-01',NULL,'1','大数据与软件工程学院','陈玉芳、邓显涛',1,22,2,3),(16,'校园报修系统','2016-01-01',NULL,'1','大数据与软件工程学院','彭麓羽、李玲、黎佳旭、黄彬恒',1,56,2,3),(17,'一念之差，一步之遥','2016-01-01',NULL,'1','大数据与软件工程学院','覃宁、杨戴菲',1,23,2,3),(18,'和谐之美','2017-01-01',NULL,'1','大数据与软件工程学院','王明珠、韦东连、覃文欣',1,25,1,2),(19,'偶动画短片制作','2017-01-01',NULL,'1','大数据与软件工程学院','王宇蒙、卢美容、莫时芬',1,22,1,2),(20,'真武道之阁','2017-01-01',NULL,'1','大数据与软件工程学院','郑远玲、韦欣、覃琦超',1,23,1,2),(21,'学而优家教网','2017-01-01',NULL,'1','大数据与软件工程学院','江妙玉、李艳芳、黎艺侠',1,43,1,2),(22,'福建土楼','2017-01-01',NULL,'1','大数据与软件工程学院','陈鑫奇、梁家漫、黄英',1,23,1,3),(23,'梧州龙母庙','2017-01-01',NULL,'1','大数据与软件工程学院','陈建英、粟秋艺、宁丹',1,25,1,3),(24,'时光·粤绣传承','2017-01-01',NULL,'1','大数据与软件工程学院','林佩迪',1,25,1,3),(25,'“互联网+扶贫”扶贫精准数据协同平台','2017-01-01',NULL,'1','大数据与软件工程学院','何志明、张燕、俸捷',1,1,1,3),(26,'制作证件照','2017-01-01',NULL,'1','大数据与软件工程学院','苏晓雪、梁琼宇、吴嘉恩',1,22,1,3),(27,'侗·情','2017-01-01',NULL,'1','大数据与软件工程学院','李曼曼、姚潇婷、周全越',1,23,1,3),(28,'渔夫日记','2017-01-01',NULL,'1','大数据与软件工程学院','刘其明、梁梦银、卓艳玲',1,26,2,1),(29,'盘扣风韵','2017-01-01',NULL,'1','大数据与软件工程学院','蔡炎真、郭田田、王春阳',1,25,2,1),(30,'人·语·鸟','2017-01-01',NULL,'1','大数据与软件工程学院','唐琨淦、彭臣军、陈家天',1,24,2,1),(31,'它们的一生','2017-01-01',NULL,'1','大数据与软件工程学院','伍慧莹',1,27,2,1),(32,'未来动物','2017-01-01',NULL,'1','大数据与软件工程学院','周全越、姚潇婷、姚潇婷',1,25,2,1),(33,'人“造”','2017-01-01',NULL,'1','大数据与软件工程学院','潘亮、李荣伟',1,24,2,1),(34,'钱塘湖春行','2017-01-01',NULL,'1','大数据与软件工程学院','梁家漫、陈建英、黄英',1,26,2,1),(35,'偶动画短片制作','2017-01-01',NULL,'1','大数据与软件工程学院','王宇蒙、卢美容、莫时芬',1,22,2,1),(36,'鸳江悟','2017-01-01',NULL,'1','大数据与软件工程学院','黄雪芳、蒋晶晶、粟秋艺',1,22,2,1),(37,'学而优家教网','2017-01-01',NULL,'1','大数据与软件工程学院','江妙玉、李艳芳、黎艺侠',1,43,2,1),(38,'和谐之美','2017-01-01',NULL,'1','大数据与软件工程学院','王明珠、韦东连、覃文欣',1,25,2,2),(39,'侗·情','2017-01-01',NULL,'1','大数据与软件工程学院','李曼曼、姚潇婷、周全越',1,23,2,2),(40,'制作证件照','2017-01-01',NULL,'1','大数据与软件工程学院','苏晓雪、梁琼宇、吴嘉恩',1,22,2,2),(41,'真武道之阁','2017-01-01',NULL,'1','大数据与软件工程学院','郑远玲、韦欣、覃琦超',1,23,2,2),(42,'福建土楼','2017-01-01',NULL,'1','大数据与软件工程学院','陈鑫奇、梁家漫、黄英',1,23,2,3),(43,'梧州龙母庙','2017-01-01',NULL,'1','大数据与软件工程学院','陈建英、粟秋艺、宁丹',1,25,2,3),(44,'时光·粤绣传承','2017-01-01',NULL,'1','大数据与软件工程学院','林佩迪',1,1,2,3),(45,'“互联网+扶贫”扶贫精准数据协同平台','2017-01-01',NULL,'1','大数据与软件工程学院','何志明、张燕、俸捷',1,43,2,3),(46,'党建智慧微云平台','2017-01-01',NULL,'1','大数据与软件工程学院','杨秀训、韦曼玲、何金燕',1,62,2,3),(47,'第二十一届全国教育教学信息化大赛：冒泡排序算法','2017-01-01',NULL,'1','大数据与软件工程学院','陈聪、农健、何高明',2,NULL,1,3),(48,'“互联网+扶贫”扶贫精准数据协同平台','2017-01-01',NULL,'1','大数据与软件工程学院','何志明、张燕、俸捷',1,1,1,1);
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_prize_grade`
--

DROP TABLE IF EXISTS `competition_prize_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_prize_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='竞赛获奖等次';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_prize_grade`
--

LOCK TABLES `competition_prize_grade` WRITE;
/*!40000 ALTER TABLE `competition_prize_grade` DISABLE KEYS */;
INSERT INTO `competition_prize_grade` VALUES (1,'一等奖'),(2,'二等奖'),(3,'三等奖');
/*!40000 ALTER TABLE `competition_prize_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_prize_level`
--

DROP TABLE IF EXISTS `competition_prize_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition_prize_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='竞赛获奖级别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_prize_level`
--

LOCK TABLES `competition_prize_level` WRITE;
/*!40000 ALTER TABLE `competition_prize_level` DISABLE KEYS */;
INSERT INTO `competition_prize_level` VALUES (1,'国家级'),(2,'区级');
/*!40000 ALTER TABLE `competition_prize_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) DEFAULT NULL,
  `dept_person` varchar(255) DEFAULT NULL,
  `dept_phone` varchar(255) DEFAULT NULL,
  `parent_dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dept_id`),
  KEY `FKhbs8yfeciw8j7sabe90qh30hl` (`parent_dept_id`),
  CONSTRAINT `FKhbs8yfeciw8j7sabe90qh30hl` FOREIGN KEY (`parent_dept_id`) REFERENCES `dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
INSERT INTO `dept` VALUES (1,'大数据与软件工程学院','黄宏本','0774-5820972',NULL),(2,'行政','龚红梅','0774-5820972',1),(3,'计算机基础','汪梅','0774-5820972',1),(7,'计算机科学与技术','代丽娴','0774-5820972',1),(8,'软件工程','何希','0774-5820972',1),(9,'数学与应用数学','覃学文','0774-5820972',1),(10,'数字媒体技术','贺杰','0774-5820972',1),(11,'物联网工程','何高明','0774-5820972',1),(12,'信息与计算科学','苏芳','0774-5820972',1);
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_menu` (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_menu_id`),
  KEY `FKlmald6k02kwl9t0j6ngirf2hp` (`role_id`),
  KEY `FKa426m23no7pwaeeg5vfqg7tfy` (`menu_id`),
  CONSTRAINT `FKa426m23no7pwaeeg5vfqg7tfy` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`),
  CONSTRAINT `FKlmald6k02kwl9t0j6ngirf2hp` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 COMMENT='角色菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES (1,6,1,'系统管理员'),(2,6,2,'系统管理员'),(3,6,3,'系统管理员'),(4,6,4,'系统管理员'),(5,6,5,'系统管理员'),(6,6,6,'公共的'),(7,6,7,'系统管理员'),(8,6,8,'系统管理员'),(9,6,9,'系统管理员'),(10,4,17,'普通老师'),(11,4,18,'普通老师'),(12,4,19,'普通老师'),(13,4,20,'普通老师'),(15,1,22,'学院院长'),(16,1,23,'学院院长'),(17,1,24,'学院院长'),(18,1,25,'学院院长'),(19,1,26,'学院院长'),(21,1,28,'学院院长'),(22,2,22,'学院副院长'),(23,2,23,'学院副院长'),(24,2,24,'学院副院长'),(31,2,25,'学院副院长'),(33,2,26,'学院副院长'),(35,2,28,'学院副院长'),(36,3,22,'学院书记'),(37,3,23,'学院书记'),(38,3,24,'学院书记'),(39,3,25,'学院书记'),(40,3,26,'学院书记'),(44,3,28,'学院书记'),(45,5,10,'部门管理员'),(46,5,11,'部门管理员'),(47,5,12,'部门管理员'),(48,5,13,'部门管理员'),(49,5,14,'部门管理员'),(51,5,16,'部门管理员'),(55,1,6,'公共的'),(56,2,6,'公共的'),(57,3,6,'公共的'),(58,4,6,'公共的'),(59,5,6,'公共的');
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_code` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_phone` varchar(255) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `job_content` varchar(255) DEFAULT NULL COMMENT '项目分工',
  `is_person` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8 COMMENT='竞赛学生信息表--弃用';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'20160007261616','卢秋树','暂无','暂无','暂无','暂无','暂无',1,1,'“绿”动大学'),(2,'20160007261616','容传毅','暂无','暂无','暂无','暂无','暂无',0,1,'“绿”动大学'),(3,'20160007261616','黄伟印','暂无','暂无','暂无','暂无','暂无',0,1,'“绿”动大学'),(4,'20160007261616','吴枝晏','暂无','暂无','暂无','暂无','暂无',1,2,'5 GreenS'),(5,'20160007261616','廖治欢','暂无','暂无','暂无','暂无','暂无',1,3,'《丢》'),(6,'20160007261616','覃宁','暂无','暂无','暂无','暂无','暂无',1,4,'一念之差，一步之遥'),(7,'20160007261616','杨戴菲','暂无','暂无','暂无','暂无','暂无',0,4,'一念之差，一步之遥'),(8,'20160007261616','潘恒飞','暂无','暂无','暂无','暂无','暂无',1,5,'周末玩乜嘢'),(9,'20160007261616','卢善坚','暂无','暂无','暂无','暂无','暂无',0,5,'周末玩乜嘢'),(10,'20160007261616','唐高杰','暂无','暂无','暂无','暂无','暂无',0,5,'周末玩乜嘢'),(11,'20160007261616','卢秋树','暂无','暂无','暂无','暂无','暂无',1,6,'“绿”动大学'),(12,'20160007261616','容传毅','暂无','暂无','暂无','暂无','暂无',0,6,'“绿”动大学'),(13,'20160007261616','黄伟印','暂无','暂无','暂无','暂无','暂无',0,6,'“绿”动大学'),(14,'20160007261616','吴枝晏','暂无','暂无','暂无','暂无','暂无',1,7,'5 GreenS'),(15,'20160007261616','廖治欢','暂无','暂无','暂无','暂无','暂无',1,8,'《丢》'),(16,'20160007261616','廖治欢','暂无','暂无','暂无','暂无','暂无',1,9,'留'),(17,'20160007261616','钟琦','暂无','暂无','暂无','暂无','暂无',1,10,'华夏之裳'),(18,'20160007261616','王安琪','暂无','暂无','暂无','暂无','暂无',0,10,'华夏之裳'),(19,'20160007261616','吴丽安','暂无','暂无','暂无','暂无','暂无',0,10,'华夏之裳'),(20,'20160007261616','姚潇婷','暂无','暂无','暂无','暂无','暂无',0,10,'华夏之裳'),(21,'20160007261616','覃燕梅','暂无','暂无','暂无','暂无','暂无',1,11,'等级官帽表情包'),(22,'20160007261616','何丽梅','暂无','暂无','暂无','暂无','暂无',0,11,'等级官帽表情包'),(23,'20160007261616','陈鑫奇','暂无','暂无','暂无','暂无','暂无',1,12,'世界文化遗产之怀远楼'),(24,'20160007261616','陈超','暂无','暂无','暂无','暂无','暂无',0,12,'世界文化遗产之怀远楼'),(25,'20160007261616','易鹏先','暂无','暂无','暂无','暂无','暂无',0,12,'世界文化遗产之怀远楼'),(26,'20160007261616','庞威','暂无','暂无','暂无','暂无','暂无',0,12,'世界文化遗产之怀远楼'),(27,'20160007261616','陆烽','暂无','暂无','暂无','暂无','暂无',1,13,'梧（吾）镇六堡——一叶品茗'),(28,'20160007261616','阳文静','暂无','暂无','暂无','暂无','暂无',0,13,'梧（吾）镇六堡——一叶品茗'),(29,'20160007261616','钟珊珊','暂无','暂无','暂无','暂无','暂无',1,14,'还世界一片绿色'),(30,'20160007261616','梁博秋','暂无','暂无','暂无','暂无','暂无',0,14,'还世界一片绿色'),(31,'20160007261616','陈玉芳','暂无','暂无','暂无','暂无','暂无',1,15,'给贫困儿童一个绿色世界'),(32,'20160007261616','邓显涛','暂无','暂无','暂无','暂无','暂无',0,15,'给贫困儿童一个绿色世界'),(33,'20160007261616','彭麓羽','暂无','暂无','暂无','暂无','暂无',1,16,'校园报修系统'),(34,'20160007261616','李玲','暂无','暂无','暂无','暂无','暂无',0,16,'校园报修系统'),(35,'20160007261616','黎佳旭','暂无','暂无','暂无','暂无','暂无',0,16,'校园报修系统'),(36,'20160007261616','黄彬恒','暂无','暂无','暂无','暂无','暂无',0,16,'校园报修系统'),(37,'20160007261616','覃宁','暂无','暂无','暂无','暂无','暂无',1,17,'一念之差，一步之遥'),(38,'20160007261616','杨戴菲','暂无','暂无','暂无','暂无','暂无',0,17,'一念之差，一步之遥'),(39,'20160007261616','王明珠','暂无','暂无','暂无','暂无','暂无',1,18,'和谐之美'),(40,'20160007261616','韦东连','暂无','暂无','暂无','暂无','暂无',0,18,'和谐之美'),(41,'20160007261616','覃文欣','暂无','暂无','暂无','暂无','暂无',0,18,'和谐之美'),(42,'20160007261616','王宇蒙','暂无','暂无','暂无','暂无','暂无',1,19,'偶动画短片制作'),(43,'20160007261616','卢美容','暂无','暂无','暂无','暂无','暂无',0,19,'偶动画短片制作'),(44,'20160007261616','莫时芬','暂无','暂无','暂无','暂无','暂无',0,19,'偶动画短片制作'),(45,'20160007261616','郑远玲','暂无','暂无','暂无','暂无','暂无',1,20,'真武道之阁'),(46,'20160007261616','韦欣','暂无','暂无','暂无','暂无','暂无',0,20,'真武道之阁'),(47,'20160007261616','覃琦超','暂无','暂无','暂无','暂无','暂无',0,20,'真武道之阁'),(48,'20160007261616','江妙玉','暂无','暂无','暂无','暂无','暂无',1,21,'学而优家教网'),(49,'20160007261616','李艳芳','暂无','暂无','暂无','暂无','暂无',0,21,'学而优家教网'),(50,'20160007261616','黎艺侠','暂无','暂无','暂无','暂无','暂无',0,21,'学而优家教网'),(51,'20160007261616','陈鑫奇','暂无','暂无','暂无','暂无','暂无',1,22,'福建土楼'),(52,'20160007261616','梁家漫','暂无','暂无','暂无','暂无','暂无',0,22,'福建土楼'),(53,'20160007261616','黄英','暂无','暂无','暂无','暂无','暂无',0,22,'福建土楼'),(54,'20160007261616','陈建英','暂无','暂无','暂无','暂无','暂无',1,23,'梧州龙母庙'),(55,'20160007261616','粟秋艺','暂无','暂无','暂无','暂无','暂无',0,23,'梧州龙母庙'),(56,'20160007261616','宁丹','暂无','暂无','暂无','暂无','暂无',0,23,'梧州龙母庙'),(57,'20160007261616','林佩迪','暂无','暂无','暂无','暂无','暂无',1,24,'时光·粤绣传承'),(58,'20160007261616','何志明','暂无','暂无','暂无','暂无','暂无',1,25,'“互联网+扶贫”扶贫精准数据协同平台'),(59,'20160007261616','张燕','暂无','暂无','暂无','暂无','暂无',0,25,'“互联网+扶贫”扶贫精准数据协同平台'),(60,'20160007261616','俸捷','暂无','暂无','暂无','暂无','暂无',0,25,'“互联网+扶贫”扶贫精准数据协同平台'),(61,'20160007261616','苏晓雪','暂无','暂无','暂无','暂无','暂无',1,26,'制作证件照'),(62,'20160007261616','梁琼宇','暂无','暂无','暂无','暂无','暂无',0,26,'制作证件照'),(63,'20160007261616','吴嘉恩','暂无','暂无','暂无','暂无','暂无',0,26,'制作证件照'),(64,'20160007261616','李曼曼','暂无','暂无','暂无','暂无','暂无',1,27,'侗·情'),(65,'20160007261616','姚潇婷','暂无','暂无','暂无','暂无','暂无',0,27,'侗·情'),(66,'20160007261616','周全越','暂无','暂无','暂无','暂无','暂无',0,27,'侗·情'),(67,'20160007261616','刘其明','暂无','暂无','暂无','暂无','暂无',1,28,'渔夫日记'),(68,'20160007261616','梁梦银','暂无','暂无','暂无','暂无','暂无',0,28,'渔夫日记'),(69,'20160007261616','卓艳玲','暂无','暂无','暂无','暂无','暂无',0,28,'渔夫日记'),(70,'20160007261616','蔡炎真','暂无','暂无','暂无','暂无','暂无',1,29,'盘扣风韵'),(71,'20160007261616','郭田田','暂无','暂无','暂无','暂无','暂无',0,29,'盘扣风韵'),(72,'20160007261616','王春阳','暂无','暂无','暂无','暂无','暂无',0,29,'盘扣风韵'),(73,'20160007261616','唐琨淦','暂无','暂无','暂无','暂无','暂无',1,30,'人·语·鸟'),(74,'20160007261616','彭臣军','暂无','暂无','暂无','暂无','暂无',0,30,'人·语·鸟'),(75,'20160007261616','陈家天','暂无','暂无','暂无','暂无','暂无',0,30,'人·语·鸟'),(76,'20160007261616','伍慧莹','暂无','暂无','暂无','暂无','暂无',1,31,'它们的一生'),(77,'20160007261616','周全越','暂无','暂无','暂无','暂无','暂无',1,32,'未来动物'),(78,'20160007261616','姚潇婷','暂无','暂无','暂无','暂无','暂无',0,32,'未来动物'),(79,'20160007261616','潘亮','暂无','暂无','暂无','暂无','暂无',1,33,'人“造”'),(80,'20160007261616','李荣伟','暂无','暂无','暂无','暂无','暂无',0,33,'人“造”'),(81,'20160007261616','梁家漫','暂无','暂无','暂无','暂无','暂无',1,34,'钱塘湖春行'),(82,'20160007261616','陈建英','暂无','暂无','暂无','暂无','暂无',0,34,'钱塘湖春行'),(83,'20160007261616','黄英','暂无','暂无','暂无','暂无','暂无',0,34,'钱塘湖春行'),(84,'20160007261616','王宇蒙','暂无','暂无','暂无','暂无','暂无',1,35,'偶动画短片制作'),(85,'20160007261616','卢美容','暂无','暂无','暂无','暂无','暂无',0,35,'偶动画短片制作'),(86,'20160007261616','莫时芬','暂无','暂无','暂无','暂无','暂无',0,35,'偶动画短片制作'),(87,'20160007261616','黄雪芳','暂无','暂无','暂无','暂无','暂无',1,36,'鸳江悟'),(88,'20160007261616','蒋晶晶','暂无','暂无','暂无','暂无','暂无',0,36,'鸳江悟'),(89,'20160007261616','粟秋艺','暂无','暂无','暂无','暂无','暂无',0,36,'鸳江悟'),(90,'20160007261616','江妙玉','暂无','暂无','暂无','暂无','暂无',1,37,'学而优家教网'),(91,'20160007261616','李艳芳','暂无','暂无','暂无','暂无','暂无',0,37,'学而优家教网'),(92,'20160007261616','黎艺侠','暂无','暂无','暂无','暂无','暂无',0,37,'学而优家教网'),(93,'20160007261616','王明珠','暂无','暂无','暂无','暂无','暂无',1,38,'和谐之美'),(94,'20160007261616','韦东连','暂无','暂无','暂无','暂无','暂无',0,38,'和谐之美'),(95,'20160007261616','覃文欣','暂无','暂无','暂无','暂无','暂无',0,38,'和谐之美'),(96,'20160007261616','李曼曼','暂无','暂无','暂无','暂无','暂无',1,39,'侗·情'),(97,'20160007261616','姚潇婷','暂无','暂无','暂无','暂无','暂无',0,39,'侗·情'),(98,'20160007261616','周全越','暂无','暂无','暂无','暂无','暂无',0,39,'侗·情'),(99,'20160007261616','苏晓雪','暂无','暂无','暂无','暂无','暂无',1,40,'制作证件照'),(100,'20160007261616','梁琼宇','暂无','暂无','暂无','暂无','暂无',0,40,'制作证件照'),(101,'20160007261616','吴嘉恩','暂无','暂无','暂无','暂无','暂无',0,40,'制作证件照'),(102,'20160007261616','郑远玲','暂无','暂无','暂无','暂无','暂无',1,41,'真武道之阁'),(103,'20160007261616','韦欣','暂无','暂无','暂无','暂无','暂无',0,41,'真武道之阁'),(104,'20160007261616','覃琦超','暂无','暂无','暂无','暂无','暂无',0,41,'真武道之阁'),(105,'20160007261616','陈鑫奇','暂无','暂无','暂无','暂无','暂无',1,42,'福建土楼'),(106,'20160007261616','梁家漫','暂无','暂无','暂无','暂无','暂无',0,42,'福建土楼'),(107,'20160007261616','黄英','暂无','暂无','暂无','暂无','暂无',0,42,'福建土楼'),(108,'20160007261616','陈建英','暂无','暂无','暂无','暂无','暂无',1,43,'梧州龙母庙'),(109,'20160007261616','粟秋艺','暂无','暂无','暂无','暂无','暂无',0,43,'梧州龙母庙'),(110,'20160007261616','宁丹','暂无','暂无','暂无','暂无','暂无',0,43,'梧州龙母庙'),(111,'20160007261616','林佩迪','暂无','暂无','暂无','暂无','暂无',1,44,'时光·粤绣传承'),(112,'20160007261616','何志明','暂无','暂无','暂无','暂无','暂无',1,45,'“互联网+扶贫”扶贫精准数据协同平台'),(113,'20160007261616','张燕','暂无','暂无','暂无','暂无','暂无',0,45,'“互联网+扶贫”扶贫精准数据协同平台'),(114,'20160007261616','俸捷','暂无','暂无','暂无','暂无','暂无',0,45,'“互联网+扶贫”扶贫精准数据协同平台'),(115,'20160007261616','杨秀训','暂无','暂无','暂无','暂无','暂无',1,46,'党建智慧微云平台'),(116,'20160007261616','韦曼玲','暂无','暂无','暂无','暂无','暂无',1,46,'党建智慧微云平台'),(117,'20160007261616','何金燕','暂无','暂无','暂无','暂无','暂无',0,46,'党建智慧微云平台');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_match`
--

DROP TABLE IF EXISTS `sys_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_match` (
  `match_id` int(11) NOT NULL AUTO_INCREMENT,
  `match_name` varchar(255) DEFAULT NULL,
  `match_level` int(11) DEFAULT NULL,
  `match_type` int(11) DEFAULT NULL,
  `organizer` varchar(255) DEFAULT NULL,
  `contractor` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`match_id`),
  KEY `FK5clc6now9tplrvticej8wp42k` (`teacher_id`),
  CONSTRAINT `FK5clc6now9tplrvticej8wp42k` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='赛事管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_match`
--

LOCK TABLES `sys_match` WRITE;
/*!40000 ALTER TABLE `sys_match` DISABLE KEYS */;
INSERT INTO `sys_match` VALUES (1,'中国大学生计算机设计大赛',1,1,'中国大学生计算机设计大赛组织委员会','梧州学院',1),(2,'第二十一届全国教育教学信息化大赛',1,2,'暂无','暂无',1),(3,'第十六届广西高等教育教学软件应用大赛',2,2,'暂无','暂无',1),(4,'第四届全区高校青年教师教学竞赛',2,2,'暂无','暂无',1),(5,'第五届全区高校青年教师教学竞赛',2,2,'暂无','暂无',1),(6,'全国软件和信息技术专业人才大赛',1,1,'暂无','暂无',1);
/*!40000 ALTER TABLE `sys_match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_match_annex`
--

DROP TABLE IF EXISTS `sys_match_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_match_annex` (
  `match_annex_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`match_annex_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='赛事附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_match_annex`
--

LOCK TABLES `sys_match_annex` WRITE;
/*!40000 ALTER TABLE `sys_match_annex` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_match_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` tinyint(4) DEFAULT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES (1,'竞赛管理','&#xe66c;','',0,'系统管理员'),(2,'教师项目管理','&#xe6b2;','',0,'系统管理员'),(3,'学术论文管理','&#xe705;','',0,'系统管理员'),(4,'教师档案管理','&#xe613;','',0,'系统管理员'),(5,'部门管理','&#xe631;','',0,'系统管理员'),(6,'基础数据管理','&#xe6b1;','',0,'系统管理员'),(7,'菜单管理','&#xe656;','',0,'系统管理员'),(8,'角色列表','&#xe66f;','',0,'系统管理员'),(9,'教师角色管理','&#xe672;','',0,'系统管理员'),(10,'竞赛管理','&#xe66c;','',0,'部门管理员'),(11,'教师项目管理','&#xe6b2;','',0,'部门管理员'),(12,'学术论文管理','&#xe705;','',0,'部门管理员'),(13,'教师档案管理','&#xe613;','',0,'部门管理员'),(14,'部门管理','&#xe631;','',0,'部门管理员'),(16,'教师角色管理','&#xe672;','',0,'部门管理员'),(17,'竞赛管理','&#xe66c;','',0,'普通老师'),(18,'教师项目管理','&#xe6b2;','',0,'普通老师'),(19,'学术论文管理','&#xe705;','',0,'普通老师'),(20,'教师档案管理','&#xe613;','',0,'普通老师'),(22,'竞赛管理','&#xe66c;','',0,'领导'),(23,'教师项目管理','&#xe6b2;','',0,'领导'),(24,'学术论文管理','&#xe705;','',0,'领导'),(25,'教师档案管理','&#xe613;','',0,'领导'),(26,'部门管理','&#xe631;','',0,'领导'),(28,'教师角色管理','&#xe672;','',0,'领导');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu_children`
--

DROP TABLE IF EXISTS `sys_menu_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu_children` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `spread` tinyint(4) DEFAULT NULL,
  `menu_menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3x9iahy8ma4rxeg4b7p9bpalg` (`menu_menu_id`),
  CONSTRAINT `FK3x9iahy8ma4rxeg4b7p9bpalg` FOREIGN KEY (`menu_menu_id`) REFERENCES `sys_menu` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu_children`
--

LOCK TABLES `sys_menu_children` WRITE;
/*!40000 ALTER TABLE `sys_menu_children` DISABLE KEYS */;
INSERT INTO `sys_menu_children` VALUES (1,'竞赛获奖列表','&#xe602;','/teacher_files_war/page/system/competition/competition-list.html',0,1),(2,'教师项目列表','&#xe602;','/teacher_files_war/page/system/teacherItem/item-list.html',0,2),(4,'学术论文列表','&#xe602;','/teacher_files_war/page/system/academicPaper/paper-list.html',0,3),(5,'教师档案列表','&#xe602;','/teacher_files_war/page/system/teacher/teacher-list.html',0,4),(6,'部门列表','&#xe602;','/teacher_files_war/page/system/dept/dept-list.html',0,5),(7,'教师列表','&#xe602;','/teacher_files_war/page/system/teacherRole/teacher-list.html',0,9),(8,'角色列表','&#xe602;','/teacher_files_war/page/system/sysrole/role-list.html',0,8),(9,'系统菜单','&#xe602;','/teacher_files_war/page/system/sysMenu/menu-list.html',0,7),(10,'二级菜单','&#xe602;','/teacher_files_war/page/system/sysMenu/children/menu-children-list.html',0,7),(11,'管理员列表','&#xe602;','/teacher_files_war/page/system/teacherRole/deptadmin-list.html',0,9),(12,'竞赛获奖列表','&#xe602;','/teacher_files_war/page/system/competition/DeptAdminAndLeader/competition-list.html',0,10),(13,'教师项目列表','&#xe602;','/teacher_files_war/page/system/teacherItem/DeptAdminAndLeader/item-list.html',0,11),(14,'学术论文列表','&#xe602;','/teacher_files_war/page/system/academicPaper/DeptAdminAndLeader/paper-list.html',0,12),(15,'教师档案列表','&#xe602;','/teacher_files_war/page/system/teacher/DeptAdminAndLeader/teacher-list.html',0,13),(16,'教研室列表','&#xe602;','/teacher_files_war/page/system/dept/DeptAdminAndLeader/dept-list.html',0,14),(17,'部门教师列表','&#xe602;','/teacher_files_war/page/system/teacherRole/DeptAdminAndLeader/teacher-list.html',0,16),(18,'竞赛获奖列表','&#xe602;','/teacher_files_war/page/system/competition/DeptAdminAndLeader/competition-list.html',0,22),(19,'我的竞赛获奖记录','&#xe602;','/teacher_files_war/page/system/competition/my-awardee-list.html',0,22),(20,'我指导的学生竞赛','&#xe602;','/teacher_files_war/page/system/competition/my-guide-list.html',0,22),(21,'教师项目列表','&#xe602;','/teacher_files_war/page/system/teacherItem/DeptAdminAndLeader/item-list.html',0,23),(22,'我主持的项目','&#xe602;','/teacher_files_war/page/system/teacherItem/my-charge-list.html',0,23),(23,'我参与的项目','&#xe602;','/teacher_files_war/page/system/teacherItem/my-join-list.html',0,23),(24,'学术论文列表','&#xe602;','/teacher_files_war/page/system/academicPaper/DeptAdminAndLeader/paper-list.html',0,24),(25,'我的学术论文','&#xe602;','/teacher_files_war/page/system/academicPaper/my-paper-list.html',0,24),(26,'教师档案列表','&#xe602;','/teacher_files_war/page/system/teacher/DeptAdminAndLeader/teacher-list.html',0,25),(27,'我的档案信息','&#xe612;','/teacher_files_war/page/system/teacher/my-info.html',0,25),(28,'教研室列表','&#xe602;','/teacher_files_war/page/system/dept/DeptAdminAndLeader/dept-list.html',0,26),(29,'部门教师列表','&#xe602;','/teacher_files_war/page/system/teacherRole/DeptAdminAndLeader/teacher-list.html',0,28),(30,'我指导的学生竞赛','&#xe602;','/teacher_files_war/page/system/competition/my-guide-list.html',0,17),(32,'我的竞赛获奖记录','&#xe602;','/teacher_files_war/page/system/competition/my-awardee-list.html',0,17),(33,'我主持的项目','&#xe602;','/teacher_files_war/page/system/teacherItem/my-charge-list.html',0,18),(34,'我参与的项目','&#xe602;','/teacher_files_war/page/system/teacherItem/my-join-list.html',0,18),(35,'我的学术论文','&#xe602;','/teacher_files_war/page/system/academicPaper/my-paper-list.html',0,19),(36,'我的档案信息','&#xe612;','/teacher_files_war/page/system/teacher/my-info.html',0,20),(37,'论文等级管理','&#xe602;','/teacher_files_war/page/system/systemBasicData/academicPaperGrade/paperGrade-list.html',0,6),(38,'赛事管理','&#xe602;','/teacher_files_war/page/system/systemBasicData/match/match-list.html',0,6),(39,'教师项目级别管理','&#xe602;','/teacher_files_war/page/system/systemBasicData/teacherItemLevel/itemLevel-list.html',0,6),(41,'教师项目类别管理','&#xe602;','/teacher_files_war/page/system/systemBasicData/teacherItemCategory/itemCategory-list.html',0,6),(42,'竞赛获奖级别管理','&#xe602;','/teacher_files_war/page/system/systemBasicData/competitionPrizeLevel/prizeLevel-list.html',0,6),(43,'竞赛获奖等次管理','&#xe602;','/teacher_files_war/page/system/systemBasicData/competitionPrizeGrade/prizeGrade-list.html',0,6);
/*!40000 ALTER TABLE `sys_menu_children` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `can_look` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,'学院院长',1),(2,'学院副院长',1),(3,'学院书记',1),(4,'教师',1),(5,'学院管理员',0),(6,'系统管理员',0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_account` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK9kulrl1tou7sch3ym55guqnxp` (`teacher_id`),
  CONSTRAINT `FK9kulrl1tou7sch3ym55guqnxp` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','admin','黄结\r',1,NULL),(2,'17041','123456','莫智懿\r',1,1),(3,'27105','123456','许传本',1,2),(4,'17224','123456','朱肖颖',1,3),(5,'17074','123456','庞光垚',1,4),(6,'17701','123456','蒲保兴',1,5),(7,'21016','123456','覃学文',1,6),(8,'30011','123456','赵春茹',1,7),(9,'21022','123456','覃桂茳',1,8),(10,'21011','123456','刘敏捷',1,9),(15,'21054','123456','杨甲山',1,13),(16,'21012','123456','黄劲',1,14),(17,'28009','123456','胡庆席',1,15),(18,'21005','123456','李连芬',1,16),(19,'21014','123456','石向东',1,17),(20,'30013','123456','涂井先',1,18),(21,'06003','123456','陈红',1,19),(22,'21018','123456','李志军',1,20),(23,'21017','123456','许成章',1,21),(24,'23098','123456','吴飞燕',1,22),(25,'17010','123456','贺杰',1,23),(28,'17044','123456','宫海晓',1,24),(29,'17062','123456','邸臻炜',1,25),(30,'17031','123456','李翊',1,26),(31,'17033','123456','黄筱佟',1,27),(32,'17214','123456','杨秋慧',1,28),(33,'17046','123456','何高明',1,29),(34,'17027','123456','蒋琳琼',1,30),(35,'17019','123456','李海英',1,31),(36,'17021','123456','李军',1,32),(37,'21010','123456','苏芳',1,33),(38,'21019','123456','赵贤',1,34),(39,'18074','123456','龚红梅',1,35),(40,'17005','123456','吴燕端',1,36),(41,'30008','123456','刘树先',1,37),(42,'17702','123456','邵晋芳',1,38),(43,'17703','123456','蔡敏仪',1,39),(44,'17704','123456','曾雨珊',1,40),(45,'17049','123456','汪梅',1,41),(46,'17020','123456','李健',1,42),(47,'17011','123456','陈佳',1,43),(48,'17022','123456','黄洁明',1,44),(49,'27013','123456','梁朝湘',1,45),(50,'17064','123456','吴芳',1,46),(51,'17018','123456','代丽娴',1,47),(52,'07003','123456','黄宏本',1,48),(53,'17037','123456','梁菁',1,49),(54,'31047','123456','李宗妮',1,50),(55,'17202','123456','农健',1,51),(56,'17132','123456','卿海军',1,52),(57,'17039','123456','陈聪',1,53),(58,'17038','123456','卢雪燕',1,54),(59,'17133','123456','黄寄洪',1,55),(60,'17213','123456','郑明',1,56),(61,'31046','123456','何希',1,57),(62,'17025','123456','吴炎桃',1,58),(63,'17221','123456','陈悦',1,59),(64,'17134','123456','冀肖榆',1,60),(65,'6688','123456','冀占江',1,61),(66,'6689','123456','陆科达',1,62),(67,'dsjxy_admin','admin','大数据与软件工程学院管理员',1,63),(68,'sys_admin','soldierhj28','系统管理员',1,64);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_code` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `teacher_sex` int(11) DEFAULT NULL,
  `teacher_birth` date DEFAULT NULL,
  `entry_time` date DEFAULT NULL,
  `teacher_img` varchar(255) DEFAULT NULL,
  `high_edu` varchar(255) DEFAULT NULL,
  `first_edu` varchar(255) DEFAULT NULL,
  `technical_post` varchar(255) DEFAULT NULL,
  `administ_post` varchar(255) DEFAULT NULL,
  `teacher_resume` varchar(2000) DEFAULT NULL,
  `other` varchar(2000) DEFAULT NULL COMMENT '主要从事工作及研究方向',
  `can_look` int(11) DEFAULT NULL,
  `role_ids` varchar(255) DEFAULT NULL,
  `role_names` varchar(255) DEFAULT NULL,
  `unit_ids` varchar(255) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  KEY `FKt9569nk9kidpx10a8lf3lq8ay` (`dept_id`),
  CONSTRAINT `FKt9569nk9kidpx10a8lf3lq8ay` FOREIGN KEY (`dept_id`) REFERENCES `dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'17041','莫智懿',1,'1981-01-01','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','硕士','本科','无','高级工程师','在国内外学术刊物上发表科研论文2 篇，其中核心刊物1篇。\n在国内外学术刊物上发表教改论文2 篇，出版专著（译著等）0 部。\n获教学科研成果奖共0项；其中：国家级0项， 省部级0项，校级0项。\n目前承担科研项目共7项；其中：国家级0项，省部级1 项，市厅级0项，校级0项，横向6项。\n目前承担教学改革项目共1项；其中：国家级0项，省部级1项，市厅级0项，校级0项。\n近三年拥有教学科研经费共319.5万元， 年均106万元。\n近三年给本科生授课（理论教学）共1173学时；指导本科毕业设计共57人次。','软件工程、应用软件技术',1,'4','教师',NULL,1),(2,'27105','许传本',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(3,'17224','朱肖颖',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(4,'17074','庞光垚',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(5,'17701','蒲保兴',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(6,'21016','覃学文',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(7,'30011','赵春茹',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(8,'21022','覃桂茳',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(9,'21011','刘敏捷',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(13,'21054','杨甲山',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(14,'21012','黄劲',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(15,'28009','胡庆席',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(16,'21005','李连芬',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(17,'21014','石向东',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(18,'30013','涂井先',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(19,'06003','陈红',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(20,'21018','李志军',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(21,'21017','许成章',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(22,'23098','吴飞燕',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(23,'17010','贺杰',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'2','学院副院长','2',1),(24,'17044','宫海晓',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(25,'17062','邸臻炜',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(26,'17031','李翊',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(27,'17033','黄筱佟',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(28,'17214','杨秋慧',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(29,'17046','何高明',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(30,'17027','蒋琳琼',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(31,'17019','李海英',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(32,'17021','李军',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(33,'21010','苏芳',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(34,'21019','赵贤',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(35,'18074','龚红梅',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(36,'17005','吴燕端',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'3','学院书记',NULL,1),(37,'30008','刘树先',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(38,'17702','邵晋芳',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(39,'17703','蔡敏仪',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(40,'17704','曾雨珊',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(41,'17049','汪梅',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(42,'17020','李健',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(43,'17011','陈佳',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(44,'17022','黄洁明',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(45,'27013','梁朝湘',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(46,'17064','吴芳',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(47,'17018','代丽娴',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(48,'07003','黄宏本',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'1','学院院长',NULL,1),(49,'17037','梁菁',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(50,'31047','李宗妮',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(51,'17202','农健',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(52,'17132','卿海军',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(53,'17039','陈聪',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(54,'17038','卢雪燕',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(55,'17133','黄寄洪',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'2','学院副院长',NULL,1),(56,'17213','郑明',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(57,'31046','何希',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(58,'17025','吴炎桃',0,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(59,'17221','陈悦',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(60,'17134','冀肖榆',1,'2019-07-24','2019-07-24','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(61,'6688','冀占江',1,'2019-07-25','2019-07-25','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(62,'6689','陆科达',1,'2019-07-25','2019-07-25','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',1,'4','教师',NULL,1),(63,'dsjxy_admin','大数据学院管理员',1,'2019-08-06','2019-08-06','/teacher_files_war/upload/teacherImg/default.png','暂无','暂无','暂无','暂无','暂无','暂无',0,'5','学院管理员',NULL,1),(64,'保密','系统管理员',1,'2019-07-25','2019-07-25','/teacher_files_war/upload/teacherImg/default.png','研究生','本科','暂无','暂无','暂无','暂无',0,'6','系统管理员',NULL,NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_item`
--

DROP TABLE IF EXISTS `teacher_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  `item_type` int(11) DEFAULT NULL COMMENT '项目类型',
  `contract_number` varchar(255) DEFAULT NULL,
  `item_money` double DEFAULT NULL,
  `item_member` varchar(255) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `item_person` int(11) DEFAULT NULL,
  `item_category_id` int(11) DEFAULT NULL,
  `item_level_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK90xtjsiqxj97k3ulv1gq9ykg7` (`item_category_id`),
  KEY `FKgmlj2mouc99otq5y0v33tu98u` (`item_level_id`),
  KEY `FKfxruiehw537yox37qls4k3hys` (`item_person`),
  CONSTRAINT `FK90xtjsiqxj97k3ulv1gq9ykg7` FOREIGN KEY (`item_category_id`) REFERENCES `teacher_item_category` (`id`),
  CONSTRAINT `FKfxruiehw537yox37qls4k3hys` FOREIGN KEY (`item_person`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `FKgmlj2mouc99otq5y0v33tu98u` FOREIGN KEY (`item_level_id`) REFERENCES `teacher_item_level` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_item`
--

LOCK TABLES `teacher_item` WRITE;
/*!40000 ALTER TABLE `teacher_item` DISABLE KEYS */;
INSERT INTO `teacher_item` VALUES (1,'用计算方法构建全基因组关联复杂疾病基因调控网络',2,'暂无',39,'56','郑明','2018-01-01','2018-01-01','2018-12-31',56,1,1),(2,'基于引力场算法的人类复杂疾病基因调控网络构建与分析',2,'暂无',25,'56','郑明','2016-01-01','2016-01-01','2016-12-31',56,1,1),(3,'有色噪声情形下多通道语音增强算法及其应用研究',2,'暂无',10,'18','涂井先','2018-01-01','2018-01-01','2018-12-31',18,2,2),(4,'数字城管视频综合管理平台构建及应用示范',2,'暂无',6,'1','莫智懿','2016-01-01','2016-01-01','2016-12-31',1,3,3),(5,'港北区脱贫攻坚辅助数据平台技术服务',2,'暂无',11.3,'1','莫智懿','2017-01-01','2017-01-01','2017-12-31',1,6,6),(6,'贵港市桂平市脱贫攻坚辅助数据平台',2,'暂无',70,'1','莫智懿','2017-01-01','2017-01-01','2017-12-31',1,6,6),(7,'贵港市港南区脱贫攻坚辅助数据平台',2,'暂无',70,'1','莫智懿','2017-01-01','2017-01-01','2017-12-31',1,6,6),(8,'贵港市覃塘区脱贫攻坚辅助数据平台',2,'暂无',70,'1','莫智懿','2017-01-01','2017-01-01','2017-12-31',1,6,6),(9,'梧州市岑溪市精准扶贫政务系统建设',2,'暂无',75,'1','莫智懿','2018-01-01','2018-01-01','2018-12-31',1,6,6),(10,'梧州市食品药品检验所报告书系统',2,'暂无',11.5,'1','莫智懿','2016-01-01','2016-01-01','2016-12-31',1,6,6),(11,'统一战线工作部网站托管和网络数据维护',2,'暂无',2,'3','朱肖颖','2018-01-01','2018-01-01','2018-12-31',3,6,6),(12,'创建全国文明城市网上工作平台托管和系统维护',2,'暂无',3,'3','朱肖颖','2018-01-01','2018-01-01','2018-12-31',3,6,6),(13,'岑溪市电子政务系统',2,'暂无',3,'60','冀肖榆','2018-01-01','2018-01-01','2018-12-31',60,6,6),(14,'苍梧县电子政务外网（二期）',2,'暂无',130,'60','冀肖榆','2017-01-01','2017-01-01','2017-12-31',60,6,6),(15,'《广西非物质文化遗产梧州龟苓膏传承人》申报片制作',2,'暂无',1.75,'22','吴飞燕','2016-01-01','2016-01-01','2016-12-31',22,6,6),(16,'《梧州市少数民族群体“四心”管控工作法宣传视频》制作',2,'暂无',3,'22','吴飞燕','2016-01-01','2016-01-01','2016-12-31',22,6,6),(17,'基于人机交互的汽车多通道显控系统界面设计研究',2,'暂无',2.3,'22','吴飞燕','2017-01-01','2017-01-01','2017-12-31',22,5,5),(18,'震荡积分和奇异积分方程的求积研究',2,'暂无',2.3,'7','赵春茹','2017-01-01','2017-01-01','2017-12-31',7,4,4),(19,'分形图像压缩的复合优化策略研究',2,'暂无',2.3,'23','贺杰','2017-01-01','2017-01-01','2017-12-31',23,5,5),(20,'港北区脱贫攻坚数据可视化系统建设',2,'暂无',9.8,'23','贺杰','2017-01-01','2017-01-01','2017-12-31',23,6,6),(21,'烟草现代零售终端智能展台',2,'暂无',10,'24','宫海晓','2019-01-01','2019-01-01','2019-12-31',24,6,6),(22,'HEVC帧内图像编码模式优化研究',2,'暂无',2.3,'24','宫海晓','2017-01-01','2017-01-01','2017-12-31',24,5,5),(23,'智慧旅游背景下MR技术与梧州市旅游项目的融合研究',2,'暂无',2.5,'27','黄筱佟','2019-01-01','2019-01-01','2019-12-31',27,5,5),(24,'局部模糊图像的模糊区域监测与分割',2,'暂无',2.4,'43','陈佳','2019-01-01','2019-01-01','2019-12-31',43,5,5),(25,'流域契约文字书数字化检索研究',2,'暂无',5,'59','陈悦','2019-01-01','2019-01-01','2019-12-31',59,5,5),(26,'基于GIS的移动端数据可视化关键技术研究',2,'暂无',2.3,'25','邸臻炜','2019-01-01','2019-01-01','2019-12-31',25,5,5),(27,'基于视频的人体行为识别研究',2,'暂无',2.2,'50','李宗妮','2019-01-01','2019-01-01','2019-12-31',50,5,5),(28,'非自治动力系统中各种跟踪性的研究',2,'暂无',2.3,'61','冀占江','2019-01-01','2019-01-01','2019-12-31',61,5,5),(29,'序列图像实时成像技术的研究',2,'暂无',2.3,'28','杨秋慧','2018-01-01','2018-01-01','2018-12-31',28,5,5),(30,'实用性半Markov自适应WSNs低占空比休眠路由协议研究',2,'暂无',2.3,'53','陈聪','2018-01-01','2018-01-01','2018-12-31',53,5,5),(31,'分水岭算法用于确定主动轮廓模型初始轮廓的算法研究',2,'暂无',2.3,'41','汪梅','2017-01-01','2017-01-01','2017-12-31',41,5,5),(32,'基于手术显微镜的可视信息全景深合成系统',2,'暂无',35,'28','杨秋慧','2019-01-01','2019-01-01','2019-12-31',28,6,6),(33,'以工程教育专业认证为导向的数字媒体技术专业人才培养模式与方法研究',1,'暂无',2,'24','宫海晓','2019-01-01','2019-01-01','2019-12-31',24,8,7),(34,'新工科背景下面向应用型本科院校计算机类专业实验实训教学体系研究—以梧州学院为例',1,'暂无',2,'51','农健','2019-01-01','2019-01-01','2019-12-31',51,8,7),(35,'新工科背景下面向应用型本科院校计算机类专业实验实训教学体系研究—以梧州学院为例',1,'暂无',1,'53','陈聪','2019-01-01','2019-01-01','2019-12-31',53,8,8),(36,'新工科背景下地方高校以学生为中心的教学模式的研究与实践',1,'暂无',2,'43','陈佳','2018-01-01','2018-01-01','2018-12-31',43,8,7),(37,'基于MOOC平台以学生为主体的教学模式的研究与实践——以《计算机应用基础》为例',1,'暂无',0.3,'43','陈佳','2017-01-01','2017-01-01','2017-12-31',43,9,9),(38,'新工科背景下应用型本科院校立体化教材建设研究',1,'暂无',1,'22','吴飞燕','2018-01-01','2018-01-01','2018-12-31',22,8,8),(39,'基于应用型本科院校的立体化教材建设研究——以《多媒体技术》课程为例',1,'暂无',0.3,'22','吴飞燕','2016-01-01','2016-01-01','2016-12-31',22,9,9),(40,'面向创新性应用型人才培养的地方院校数字媒体技术专业转型发展研究',1,'暂无',1,'23','贺杰','2018-01-01','2018-01-01','2018-12-31',23,8,8),(41,'面向创新性应用型人才培养的数字媒体技术专业转型发展研究',1,'暂无',0.3,'23','贺杰','2017-01-01','2017-01-01','2017-12-31',23,9,9),(42,'真实职场环境下行业软件技术政校企协同育人平台建设及实训教学改革',1,'暂无',2,'62','陆科达','2017-01-01','2017-01-01','2017-12-31',62,8,7),(43,'多层次信息安全技术联赛启发下的信息安全应用型人才培养模式探索',1,'暂无',0.3,'55','黄寄洪','2016-01-01','2016-01-01','2016-12-31',55,9,9),(44,'中职升本高等数学课程体系改革的研究',1,'暂无',0.3,'21','许成章','2016-01-01','2016-01-01','2016-12-31',21,9,9),(45,'体验性教学在计算机专业课程中的应用',1,'暂无',0.3,'28','杨秋慧','2017-01-01','2017-01-01','2017-12-31',28,9,9),(46,'中职升本人才培养模式及相关政策研究',1,'暂无',0.3,'2','许传本','2017-01-01','2017-01-01','2017-12-31',2,9,9);
/*!40000 ALTER TABLE `teacher_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_item_annex`
--

DROP TABLE IF EXISTS `teacher_item_annex`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_item_annex` (
  `item_annex_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_path` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_annex_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='教师项目附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_item_annex`
--

LOCK TABLES `teacher_item_annex` WRITE;
/*!40000 ALTER TABLE `teacher_item_annex` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_item_annex` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_item_category`
--

DROP TABLE IF EXISTS `teacher_item_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_item_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='教师项目类别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_item_category`
--

LOCK TABLES `teacher_item_category` WRITE;
/*!40000 ALTER TABLE `teacher_item_category` DISABLE KEYS */;
INSERT INTO `teacher_item_category` VALUES (1,'国家级地区项目'),(2,'省级地区项目'),(3,'广西科技厅科技开发项目'),(4,'广西教育厅科技开发项目'),(5,'广西中青年教师基础能力提升项目'),(6,'横向'),(7,'纵向'),(8,'广西高等教育本科教学改革工程项目'),(9,'梧州学院教育教学改革工程项目');
/*!40000 ALTER TABLE `teacher_item_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_item_level`
--

DROP TABLE IF EXISTS `teacher_item_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_item_level` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='教师项目级别';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_item_level`
--

LOCK TABLES `teacher_item_level` WRITE;
/*!40000 ALTER TABLE `teacher_item_level` DISABLE KEYS */;
INSERT INTO `teacher_item_level` VALUES (1,'国家自然科学基金'),(2,'省级自然科学基金'),(3,'省级科技开发项目'),(4,'教育厅科技开发项目'),(5,'教育厅科研项目'),(6,'社会服务'),(7,'一般项目A类'),(8,'一般项目B类'),(9,'一般项目');
/*!40000 ALTER TABLE `teacher_item_level` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-10 16:32:32
