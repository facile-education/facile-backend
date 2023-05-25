package com.weprode.nero.news.search;

import com.liferay.document.library.kernel.service.DLAppServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.HtmlParserUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.weprode.nero.news.model.News;
import com.weprode.nero.news.model.NewsAttachedFile;
import com.weprode.nero.news.model.NewsPopulation;
import com.weprode.nero.news.service.NewsAttachedFileLocalServiceUtil;
import com.weprode.nero.news.service.NewsPopulationLocalServiceUtil;
import org.osgi.service.component.annotations.Component;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = "indexer.class.name=com.weprode.nero.news.model.News",
        service = ModelDocumentContributor.class
)
public class NewsModelDocumentContributor
        implements ModelDocumentContributor<News> {

    @Override
    public void contribute(Document document, News news) {
        try {
            // titre de news,
            // contenu de news,
            // auteur,
            // nom des pièces jointes,
            //TODO contenu textuel des pièces jointes
            document.addDate(Field.MODIFIED_DATE, news.getModificationDate());
            document.addText(Field.TITLE, normalize(news.getTitle()));
            document.addNumber(Field.COMPANY_ID, news.getCompanyId());

            document.addText(Field.CONTENT, normalize(HtmlParserUtil.extractText(news.getContent())));
            document.addDate(Field.DISPLAY_DATE, news.getPublicationDate());
            document.addNumber(Field.USER_ID, news.getAuthorId());
            User author = UserLocalServiceUtil.getUser(news.getAuthorId());
            document.addText(Field.USER_NAME, normalize(author.getFullName()));
            document.addNumber(Field.TYPE, news.isIsSchoolNews() ? 1 : 0);

            List<NewsPopulation> populations = NewsPopulationLocalServiceUtil.getNewsPopulations(news.getNewsId());
            String[] populationIds = new String[populations.size()];
            List<Long> groupIds = new ArrayList<>();
            groupIds.add(author.getGroupId());

            int index = 0;
            for (NewsPopulation population : populations) {
                if (!groupIds.contains(population.getGroupId())) {
                    groupIds.add(population.getGroupId());
                }
                populationIds[index++] = population.getGroupId() + "_" + population.getRoleId();
            }
            document.addNumber(Field.SCOPE_GROUP_ID, groupIds.stream().mapToLong(l -> l).toArray());
            document.addNumber(Field.GROUP_ID, groupIds.stream().mapToLong(l -> l).toArray());
            document.addText("populationIds", populationIds);

            List<String> fileNames = new ArrayList<>();

            for (NewsAttachedFile attachedFile : NewsAttachedFileLocalServiceUtil.getNewsAttachedFiles(news.getNewsId())) {
                FileEntry attachment = DLAppServiceUtil.getFileEntry(attachedFile.getFileId());
                fileNames.add(normalize(attachment.getTitle()));
            }

            String[] attachmentNames = fileNames.toArray(String[]::new);
            document.addText(Field.ASSET_TAG_NAMES, attachmentNames);

            logger.info("Added news fields to document " + document);
        } catch (PortalException e) {
            logger.warn("Unable to index news " + news.getNewsId(), e);
        }
    }

    private String normalize(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFKD).replaceAll("\\p{M}", "");
    }

    private static final Log logger = LogFactoryUtil.getLog(
            NewsModelDocumentContributor.class);
}
