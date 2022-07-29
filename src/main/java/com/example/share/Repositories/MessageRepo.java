package com.example.share.Repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.share.Entities.Messages;
@Repository
public interface MessageRepo extends JpaRepository<Messages, Long> {
	@Query(value = "select distinct s.msg_id,s.msg_id,s.user_msg,s.date from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id left join deletee d on d.msg_id=s.msg_id where (f.std_id=?1 and t.std_id=?2)",nativeQuery = true)
    Collection<Messages> getAllMsgSentById(long from,long to);
	@Query(value = "select * from message s inner join grp_messages g on g.msg_id=s.msg_id where (g.group_id=?2 and s.msg_id not in (select msg_id from msgfrom_std where std_id=?1))",nativeQuery = true)
	Collection<Messages> getAllGroupSendById(long from,long to);
	@Query(value = "select distinct m.msg_id,m.user_msg,m.date from message m inner join grp_messages g on g.msg_id=m.msg_id inner join msgfrom_std f on f.msg_id=m.msg_id inner join seen ms on ms.msg_id=m.msg_id inner join grp_participantss gp on gp.group_id=g.group_id where gp.st_id=?1 and not f.std_id=?1 and gp.group_id not in (select s.grp_id from left_group s where s.std_id=?1) and m.msg_id not in (select d.msg_id from deletee d where d.std_id=?1)",nativeQuery = true)
	Collection<Messages> getAllReceivedGroupSendById(long ownid);
	@Query(value = "select distinct m.msg_id,m.user_msg,m.date from message m inner join grp_messages g on g.msg_id=m.msg_id inner join msgfrom_std f on f.msg_id=m.msg_id inner join seen ms on ms.msg_id=m.msg_id inner join grp_participantss gp on gp.group_id=g.group_id where gp.st_id=?1 and not f.std_id=?1 and gp.group_id not in (select s.grp_id from left_group s where s.std_id=?1) and m.msg_id not in (select d.msg_id from deletee d where d.std_id=?1) and g.group_id=?2",nativeQuery = true)
	Collection<Messages> getAllReceivedSpecificGroupSendById(long ownid,long grpid);
	@Query(value = "select count(*),g.group_id from message m inner join grp_messages g on g.msg_id=m.msg_id left join deletee d on d.msg_id=m.msg_id inner join msgfrom_std f on f.msg_id=m.msg_id inner join seen ms on ms.msg_id=m.msg_id inner join grp_participantss gp on gp.group_id=g.group_id where gp.st_id=?1 and not f.std_id=?1 and gp.st_id not in (select s.std_id from message m inner join seen s on s.msg_id=m.msg_id) and gp.group_id not in (select s.grp_id from left_group s where s.std_id=?1) group by g.group_id",nativeQuery = true)
	Collection<Object[]> getAllReceivedGroupCountSendById(long ownid);
	@Query(value = "select distinct m.msg_id,m.user_msg,m.date from message m inner join grp_messages g on g.msg_id=m.msg_id left join deletee d on d.msg_id=m.msg_id where g.group_id=?1 and m.msg_id not in (select m.msg_id from message m inner join msg_cleared mc on mc.msg_id=m.msg_id where mc.std_id=?2)",nativeQuery = true)
	Collection<Messages> getAllGroupMSGSById(long grp,long ownid);
	@Query(value = "select distinct m.msg_id,m.user_msg,m.date from message m inner join grp_messages g on g.msg_id=m.msg_id left join deletee d on d.msg_id=m.msg_id inner join seen mc on mc.msg_id=m.msg_id where g.group_id=?1 and not mc.std_id=?4",nativeQuery = true)
	Collection<Messages> getAllGroupNotSeenMSGSById(long grp,int status1,long ownid);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join grp_messages g on g.msg_id=s.msg_id left join deletee d on d.msg_id=s.msg_id where (f.std_id=?1 and g.group_id=?2 or s.msg_id in (select m.msg_id from message m inner join msgfrom_std f on f.msg_id=m.msg_id where f.std_id=?1))",nativeQuery = true)
	Collection<Messages> getAllGroupSentById(long from,long to);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id where (f.std_id=?1 and t.std_id=?2 and s.msg_id=?3)",nativeQuery = true)
	Messages getMessageByid(long from,long to,long msgid);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id inner join msg_to_std t on t.msg_id=s.msg_id left join deletee d on d.msg_id=s.msg_id where t.std_id=?1 and s.msg_id not in (select m.msg_id from message m inner join seen sn on sn.msg_id=m.msg_id where sn.std_id=?1)",nativeQuery = true)
	Collection<Messages> totalMessagesReceivedById(long ownid);
	@Query(value = "select * from message s inner join msgfrom_std f on f.msg_id=s.msg_id left join msg_to_std t on t.msg_id=s.msg_id where f.std_id=?1 or t.std_id=?1",nativeQuery = true)
	Collection<Messages> allMSGSentOrReceivById(long ownid);


	@Query(value = "select * from message m inner join achieve a on a.msg_id=m.msg_id inner join grp_messages gm on gm.msg_id=a.msg_id where a.std_id=?1 and a.status=?2",nativeQuery = true)
	Collection<Messages> allGrpMsgsAchievedById(long ownid,int status);
	@Query(value = "select * from message m inner join achieve a on a.msg_id=m.msg_id inner join msg_to_std tm on tm.msg_id=a.msg_id where a.std_id=?1 and a.status=?2",nativeQuery = true)
	Collection<Messages> allIndividualMsgsAchievedById(long ownid,int status);
	@Query(value = "select * from message m where m.msg_id=?1",nativeQuery = true)
	Messages findMsgById(long id);
	@Query(value = "select * from message m where m.msg_id=?1",nativeQuery = true)
	Collection<Messages> fingByidd(long id);
	@Query(value = "select * from message s inner join seen sn on sn.msg_id=s.msg_id inner join grp_messages gm on gm.msg_id=s.msg_id where sn.std_id=?1 and gm.group_id not in (select s.grp_id from left_group s where s.std_id=?1) and s.msg_id not in (select d.msg_id from deletee d where d.std_id=?1) and gm.group_id=?2",nativeQuery = true)
	Collection<Messages> fingSpecifricGrpMsgNotSeenById(long ownid,long grp);
	@Query(value = "select * from message m inner join seen s on s.msg_id=m.msg_id",nativeQuery = true)
	Collection<Messages> fingMsgSeenByidd();
	@Query(value = "select * from message s inner join seen sn on sn.msg_id=s.msg_id inner join grp_messages gm on gm.msg_id=s.msg_id where sn.std_id=?1 and gm.group_id not in (select s.grp_id from left_group s where s.std_id=?1) and s.msg_id not in (select d.msg_id from deletee d where d.std_id=?1)",nativeQuery = true)
	Collection<Messages> fingMsgSeenLoggerByidd(long ownid);
	@Query(value = "select * from message m inner join msgfrom_std f on f.msg_id=m.msg_id  where f.std_id=?1 and m.msg_id not in (select m.msg_id from message m inner join deletee d on d.msg_id=m.msg_id inner join msgfrom_std f on f.msg_id=m.msg_id where f.std_id=?1)",nativeQuery = true)
	Collection<Messages> allMsgsentById(long ownid);
}
