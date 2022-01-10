FROM adoptopenjdk/openjdk16:alpine
EXPOSE 8080:8080
RUN mkdir /app
COPY ./build/install/workshop-resilient-api-integration/ /app/
WORKDIR /app/bin
CMD ["sh", "workshop-resilient-api-integration"]