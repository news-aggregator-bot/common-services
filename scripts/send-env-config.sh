cd ~/.ssh
echo "SendEnv SPRING_PROFILES_ACTIVE" >> tempconfig
echo "SendEnv MYSQL_ROOT_PASSWORD" >> tempconfig
value=$(<config)
echo "$value" >> tempconfig
cp tempconfig config
cat tempconfig
rm tempconfig