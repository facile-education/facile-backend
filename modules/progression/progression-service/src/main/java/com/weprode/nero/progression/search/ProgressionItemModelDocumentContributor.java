package com.weprode.nero.progression.search;

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
import com.weprode.nero.progression.constants.ProgressionConstants;
import com.weprode.nero.progression.model.ItemContent;
import com.weprode.nero.progression.model.Progression;
import com.weprode.nero.progression.model.ProgressionItem;
import com.weprode.nero.progression.service.ItemContentLocalServiceUtil;
import com.weprode.nero.progression.service.ProgressionLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.progression.model.ProgressionItem",
        service = ModelDocumentContributor.class
)
public class ProgressionItemModelDocumentContributor
        implements ModelDocumentContributor<ProgressionItem> {

    @Override
    public void contribute(Document document, ProgressionItem progressionItem) {
        try {
            document.addDate(Field.MODIFIED_DATE, progressionItem.getModifiedDate());
            document.addText(Field.TITLE, progressionItem.getItemName());
            document.addNumber(Field.COMPANY_ID, progressionItem.getCompanyId());
            Progression progression = ProgressionLocalServiceUtil.getProgression(progressionItem.getProgressionId());
            User author = UserLocalServiceUtil.getUser(progression.getTeacherId());
            document.addNumber(Field.GROUP_ID, author.getGroupId());
            document.addNumber(Field.SCOPE_GROUP_ID, author.getGroupId());

            Role userRole = RoleLocalServiceUtil.getRole(progressionItem.getCompanyId(), RoleConstants.USER);
            document.addText(Field.ROLE_ID, new String[]{String.valueOf(userRole.getRoleId())});

            StringBuilder content = new StringBuilder();
            for (ItemContent itemContent : ItemContentLocalServiceUtil.getContentsByItemId(progressionItem.getProgressionItemId())) {
                if (itemContent.getContentType() == ProgressionConstants.TYPE_TEXT) {
                    content.append(itemContent.getContentValue());
                }
            }

            document.addText(Field.CONTENT, HtmlParserUtil.extractText(content.toString()));
            document.addDate(Field.DISPLAY_DATE, progressionItem.getModifiedDate());
            document.addNumber(Field.USER_ID, progression.getTeacherId());
            document.addText(Field.USER_NAME, author.getFullName());
            document.addNumber(Field.TYPE, progressionItem.isIsHomework() ? ProgressionConstants.HOMEWORK_TYPE : ProgressionConstants.SESSION_TYPE);

            // TODO link, attachments ?
            List<String> fileNames = new ArrayList<>();

            String[] attachmentNames = fileNames.toArray(String[]::new);
            document.addText(Field.ASSET_TAG_NAMES, attachmentNames);

            logger.debug("Added progressionItem fields to document " + document);
        } catch (PortalException e) {
            logger.warn("Unable to index progressionItem " + progressionItem.getProgressionItemId(), e);
        }
    }

    private static final Log logger = LogFactoryUtil.getLog(
            ProgressionItemModelDocumentContributor.class);
}
