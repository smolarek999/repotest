CREATE VIEW `v_user_role` AS

SELECT  u.username, u.password, a.authorityname as group_name

 FROM `Users_Authorities` ua

 INNER JOIN `Users` u ON u.userId = ua.userId

 INNER JOIN `Authorities` a ON a.authorityId =  ua.authorityId; 