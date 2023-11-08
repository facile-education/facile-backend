CONTEXT_NAME=lfr
ROOT_CONTEXT=ROOT
ROOT_WEBAPP=/opt/liferay/tomcat/webapps/$ROOT_CONTEXT

if [[ -d "$ROOT_WEBAPP" ]]
then
    echo "Updating $ROOT_CONTEXT context to $CONTEXT_NAME."

    # Rename tomcat/webbaps/ROOT folder
    mv $ROOT_WEBAPP /opt/liferay/tomcat/webapps/$CONTEXT_NAME
fi

if [[ -f /opt/liferay/tomcat/conf/Catalina/localhost/$ROOT_CONTEXT.xml ]]
then
    echo "Renaming $ROOT_CONTEXT.xml to $CONTEXT_NAME.xml"

    # Rename context configuration file
    mv /opt/liferay/tomcat/conf/Catalina/localhost/$ROOT_CONTEXT.xml /opt/liferay/tomcat/conf/Catalina/localhost/$CONTEXT_NAME.xml
fi

# Replace ROOT occurences with new context
sed -i "s|$ROOT_CONTEXT|$CONTEXT_NAME|g" /opt/liferay/tomcat/conf/catalina.properties
