package com.weprode.nero.progression.search;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.weprode.nero.progression.model.Progression;
import org.osgi.service.component.annotations.Component;

import java.text.Normalizer;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.progression.model.Progression",
        service = ModelDocumentContributor.class
)
public class ProgressionModelDocumentContributor
        implements ModelDocumentContributor<Progression> {

    @Override
    public void contribute(Document document, Progression progression) {
        try {
            document.addDate(Field.MODIFIED_DATE, progression.getCreateDate());
            document.addText(Field.TITLE, normalize(progression.getName()));
            document.addNumber(Field.COMPANY_ID, progression.getCompanyId());
            User author = UserLocalServiceUtil.getUser(progression.getTeacherId());
            document.addNumber(Field.GROUP_ID, author.getGroupId());
            document.addNumber(Field.SCOPE_GROUP_ID, author.getGroupId());

            document.addText(Field.DESCRIPTION, normalize(HtmlParserUtil.extractText(progression.getDescription())));
            document.addDate(Field.DISPLAY_DATE, progression.getCreateDate());
            document.addNumber(Field.USER_ID, progression.getTeacherId());
            document.addText(Field.USER_NAME, normalize(author.getFullName()));

            logger.info("Added progression fields to document " + document);
        } catch (PortalException e) {
            logger.warn("Unable to index progression " + progression.getProgressionId(), e);
        }
    }

    private String normalize(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
    }

    private static final Log logger = LogFactoryUtil.getLog(
            ProgressionModelDocumentContributor.class);
}