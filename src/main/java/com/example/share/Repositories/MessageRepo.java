package com.example.share.Repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.share.Entities.Messages;

public interface MessageRepo extends JpaRepository<Messages, Long> {
	@Query(value = "select distinct s.msg_id,s.msg_id,s.user_msg,s.date from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id left join std_delete sd on sd.msg_id=s.msg_id where (f.std_id=?1 and t.std_id=?2 and (d.delete_id=?3 or not sd.st_id=?2) and a.achieve_id=?4)",nativeQuery = true)
    Collection<Messages> getAllMsgSentById(long from,long to,int status1,int status2);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join grp_messages g on g.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id left join std_delete sd on sd.msg_id=s.msg_id where (not f.std_id=?1 and g.group_id=?2 and (d.delete_id=?3 or not sd.st_id=?1) and a.achieve_id=?3 or s.msg_id in (select m.msg_id from message m left join std_archieve a on a.msg_id=m.msg_id inner join msgfrom_std f on f.msg_id=m.msg_id where not a.st_id=?1 and not f.std_id=?1))",nativeQuery = true)
	Collection<Messages> getAllGroupSendById(long from,long to,int status1);
	@Query(value = "select distinct m.msg_id,m.user_msg,m.date from message m inner join grp_messages g on g.msg_id=m.msg_id inner join msg_deleted d on d.msg_id=m.msg_id inner join msg_archieved a on a.msg_id=m.msg_id inner join msgfrom_std f on f.msg_id=m.msg_id inner join seen ms on ms.msg_id=m.msg_id inner join grp_participants gp on gp.group_id=g.group_id where d.delete_id=?2 and a.achieve_id=?2 and gp.st_id=?1 and not f.std_id=?1 and gp.st_id not in (select s.std_id from message m inner join seen s on s.msg_id=m.msg_id) and gp.group_id not in (select s.grp_id from left_group s where s.std_id=?1)",nativeQuery = true)
	Collection<Messages> getAllReceivedGroupSendById(long ownid,int status2);
	@Query(value = "select distinct m.msg_id,m.user_msg,m.date from message m inner join grp_messages g on g.msg_id=m.msg_id inner join msg_deleted d on d.msg_id=m.msg_id inner join msg_archieved a on a.msg_id=m.msg_id left join std_delete sd on sd.msg_id=m.msg_id where g.group_id=?1 and (d.delete_id=?2 or not sd.st_id=?4) and  m.msg_id not in (select m.msg_id from message m inner join msg_cleared mc on mc.msg_id=m.msg_id where mc.std_id=?4) and a.achieve_id=?3",nativeQuery = true)
	Collection<Messages> getAllGroupMSGSById(long grp,int status1,int status2,long ownid);
	@Query(value = "select distinct m.msg_id,m.user_msg,m.date from message m inner join grp_messages g on g.msg_id=m.msg_id inner join msg_deleted d on d.msg_id=m.msg_id inner join msg_archieved a on a.msg_id=m.msg_id left join std_delete sd on sd.msg_id=m.msg_id inner join seen mc on mc.msg_id=m.msg_id where g.group_id=?1 and (d.delete_id=?3 or not sd.st_id=?4) and not mc.std_id=?4 and a.achieve_id=?2",nativeQuery = true)
	Collection<Messages> getAllGroupNotSeenMSGSById(long grp,int status1,int status2,long ownid);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join grp_messages g on g.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id where (f.std_id=?1 and g.group_id=?2 and (d.delete_id=?3) and a.achieve_id=?3 or s.msg_id in (select m.msg_id from message m left join std_archieve a on a.msg_id=m.msg_id inner join msgfrom_std f on f.msg_id=m.msg_id where not a.st_id=?1 and f.std_id=?1))",nativeQuery = true)
	Collection<Messages> getAllGroupSentById(long from,long to,int status1);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id where (f.std_id=?1 and t.std_id=?2 and s.msg_id=?3)",nativeQuery = true)
	Messages getMessageByid(long from,long to,long msgid);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id  where t.std_id=?1 and d.delete_id=?2 and a.achieve_id=?2 and s.msg_id not in (select m.msg_id from message m inner join seen sn on sn.msg_id=m.msg_id where sn.std_id=?1)",nativeQuery = true)
	Collection<Messages> totalMessagesReceivedById(long ownid,int status);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id left join msg_to_std t on t.msg_id=s.msg_id where f.std_id=?1 or t.std_id=?1",nativeQuery = true)
	Collection<Messages> allMSGSentOrReceivById(long ownid);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id where (t.std_id=?1 and f.std_id=?2) or (t.std_id=?2 and f.std_id=?1) and a.achieve_id=?3",nativeQuery = true)
	Collection<Messages> allAchievedById(long ownid,long fomid,int status);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id where (t.std_id=?1 or f.std_id=?1) and a.achieve_id=?2 and d.delete_id=?3",nativeQuery = true)
	Collection<Messages> allAchievedById(long ownid,int status,int status1);
	@Query(value = "select * from message m where m.msg_id=?1",nativeQuery = true)
	Messages findMsgById(long id);
	@Query(value = "select * from message m where m.msg_id=?1",nativeQuery = true)
	Collection<Messages> fingByidd(long id);
	@Query(value = "select * from message m inner join seen s on s.msg_id=m.msg_id",nativeQuery = true)
	Collection<Messages> fingMsgSeenByidd();
	@Query(value = "select * from message s inner join grp_messages g on g.msg_id=s.msg_id inner join msg_deleted d on d.msg_id=s.msg_id inner join msg_archieved a on a.msg_id=s.msg_id inner join std_archieve sa on sa.msg_id=s.msg_id where a.achieve_id=?1 and d.delete_id=?2 and sa.st_id=?3",nativeQuery = true)
	Collection<Messages> findAllgrpMsgsArchievedById(long status1,long status,long ownid);
	@Query(value = "select * from message m inner join msg_deleted d on d.msg_id=m.msg_id inner join msgfrom_std f on f.msg_id=m.msg_id where d.delete_id=?2 and f.std_id=?1",nativeQuery = true)
	Collection<Messages> allMsgsentById(long ownid,int status);
}
