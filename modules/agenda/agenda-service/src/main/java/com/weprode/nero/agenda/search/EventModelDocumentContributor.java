package com.weprode.nero.agenda.search;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.weprode.nero.agenda.model.Event;
import com.weprode.nero.agenda.model.EventPopulation;
import com.weprode.nero.agenda.service.EventPopulationLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.agenda.model.Event",
        service = ModelDocumentContributor.class
)
public class EventModelDocumentContributor
        implements ModelDocumentContributor<Event> {

    @Override
    public void contribute(Document document, Event event) {
        try {
            // titre de event,
            // contenu de event,
            // auteur,
            // nom des pièces jointes,
            document.addDate(Field.MODIFIED_DATE, event.getStartDate());
            document.addText(Field.TITLE, event.getTitle());
            document.addNumber(Field.COMPANY_ID, event.getCompanyId());

            document.addText(Field.CONTENT, HtmlParserUtil.extractText(event.getDescription()));
            document.addDate(Field.DISPLAY_DATE, event.getStartDate());
            document.addNumber(Field.USER_ID, event.getAuthorId());
            User author = UserLocalServiceUtil.getUser(event.getAuthorId());
            document.addText(Field.USER_NAME, author.getFullName());

            Role userRole = RoleLocalServiceUtil.getRole(event.getCompanyId(), RoleConstants.USER);
            document.addText(Field.ROLE_ID, new String[]{String.valueOf(userRole.getRoleId())});

            List<EventPopulation> populations = EventPopulationLocalServiceUtil.getEventPopulations(event.getEventId());
            String[] populationIds = new String[populations.size()];
            List<Long> groupIds = new ArrayList<>();
            groupIds.add(author.getGroupId());

            int index = 0;
            for (EventPopulation population : populations) {
                if (!groupIds.contains(population.getGroupId())) {
                    groupIds.add(population.getGroupId());
                }
                populationIds[index++] = population.getGroupId() + "_" + population.getRoleId();
            }
            document.addNumber(Field.SCOPE_GROUP_ID, groupIds.stream().mapToLong(l -> l).toArray());
            document.addNumber(Field.GROUP_ID, groupIds.stream().mapToLong(l -> l).toArray());
            document.addText("populationIds", populationIds);

            logger.debug("Added event fields to document " + document);
        } catch (PortalException e) {
            logger.warn("Unable to index event " + event.getEventId(), e);
        }
    }

    private static final Log logger = LogFactoryUtil.getLog(
            EventModelDocumentContributor.class);
}
