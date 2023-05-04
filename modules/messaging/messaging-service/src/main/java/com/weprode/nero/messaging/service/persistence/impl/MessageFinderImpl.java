package com.weprode.nero.messaging.service.persistence.impl;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.*;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.weprode.nero.messaging.model.Message;
import com.weprode.nero.messaging.service.MessageLocalServiceUtil;
import com.weprode.nero.messaging.service.persistence.MessageFinder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Component(service = MessageFinder.class)
public class MessageFinderImpl extends MessageFinderBaseImpl
        implements MessageFinder {

    @Reference
    private CustomSQL customSQL;

    @Reference
    private BasePersistence<Message> basePersistence;

    public static final String QUERY_SELECT_MESSAGE_THREAD =
            MessageFinder.class.getName() + ".getUserThreadMessages";

    private static final Log logger = LogFactoryUtil.getLog(MessageFinderImpl.class.getName());

    public List<Message> getUserThreadMessages(long userId, long threadId) {
        List<Message> result = new ArrayList<>();

        Session session = null;
        try {
            session = openSession();
            String sql = customSQL.get(getClass(), QUERY_SELECT_MESSAGE_THREAD);

            SQLQuery query = session.createSQLQuery(sql);
            QueryPos qPos = QueryPos.getInstance(query);
            qPos.add(userId);
            qPos.add(threadId);

            List<BigInteger> messageIds = (List<BigInteger>) QueryUtil.list(query, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
            for (BigInteger messageId : messageIds) {
                result.add(MessageLocalServiceUtil.getMessage(messageId.longValue()));
            }

            return result;
        } catch (Exception e) {
            logger.error("Exception while running custom query for IM user thread messages for userId= "+userId + " and threadId="+threadId, e);
        } finally {
            closeSession(session);
        }

        return result;
    }

    public List<User> findMessageRecipients(Long[] userIds) {
        Session session = null;

        try {
            session = basePersistence.openSession();

            ClassLoader classLoader = getClass().getClassLoader();
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class, classLoader);

            if (userIds != null && userIds.length > 0) {
                dynamicQuery.add(PropertyFactoryUtil.forName("userId").in(userIds));
                try {
                    return UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                } catch (Exception e) {
                    logger.error("Error while retrieving the list of recipients", e);
                }
            }
        } catch (Exception e) {
            logger.error("Dynamic query failed", e);
        } finally {
            basePersistence.closeSession(session);
        }

        return new ArrayList<>();
    }
}
