
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_test_log  测试日志表
-- ----------------------------
DROP TABLE IF EXISTS `t_test_log`;
CREATE TABLE `t_test_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logContent` varchar(4000) NOT NULL COMMENT '日志内容',
  `createts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '日志生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
