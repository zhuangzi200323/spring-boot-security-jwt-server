-- ----------------------------
-- Records of users
-- ----------------------------
-- 密码是123456
REPLACE INTO `users` VALUES (1, 'admin', '$2a$10$Q0GkB6bAY18mrkbVAkHE/eCvYNW/Kz1TfkJZi3RSVE8SHHMNKlDtq', '');
REPLACE INTO `users` VALUES (2, 'test', '$2a$10$Q0GkB6bAY18mrkbVAkHE/eCvYNW/Kz1TfkJZi3RSVE8SHHMNKlDtq', '');


-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
REPLACE INTO `oauth_client_details` VALUES ('client1', 'resource1', '$2a$10$Q0GkB6bAY18mrkbVAkHE/eCvYNW/Kz1TfkJZi3RSVE8SHHMNKlDtq', 'scope1', 'authorization_code,password,client_credentials,implicit,refresh_token', 'http://www.baidu.com', NULL, 7200, 259200, NULL, 'false');


-- ----------------------------
-- Records of role
-- ----------------------------
REPLACE INTO `roles` VALUES (1, '管理员');
REPLACE INTO `roles` VALUES (2, '会员');

-- ----------------------------
-- Records of user_role
-- ----------------------------
REPLACE INTO `user_role` VALUES (1, 1, 1);
REPLACE INTO `user_role` VALUES (2, 2, 2);

-- ----------------------------
-- Records of role_permission
-- ----------------------------
REPLACE INTO `role_permission` VALUES (1, 1, 1);
REPLACE INTO `role_permission` VALUES (2, 1, 2);
REPLACE INTO `role_permission` VALUES (3, 2, 4);

-- ----------------------------
-- Records of permission
-- ----------------------------
REPLACE INTO `permissions` VALUES (1, 'p1', '测试资源1', '/r/r1');
REPLACE INTO `permissions` VALUES (2, 'p3', '测试资源2', '/r/r2');
REPLACE INTO `permissions` VALUES (3, 'p4', 'test3', '/r/r3');
REPLACE INTO `permissions` VALUES (4, 'p2', 'test2', '/r/r2');