# Linear regression
# show all data sets in R
data()
# learn about swiss
?swiss
#
str(swiss)
#
head(swiss)

attach(swiss)
# build a predicting model
plot(swiss$Fertility~swiss$Education)

lm1 <- lm(Fertility~Education, data=swiss)
summary(lml)

#education had a close P value

# 3.2
cov(swiss$Fertility,swiss$Education)
cor(swiss$Fertility,swiss$Education)



#3.5
# use model with all predictors ~.
lm2 <- lm(Fertility~., data=swiss)
summary(lm2)

lm3 <- lm(Fertility~Education+Catholic+Infant.Mortality, data=swiss)
summary(lm3)

#lm2 is best model
anova(lm1,lm2,lm3)