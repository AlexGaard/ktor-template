FROM gcr.io/distroless/java17-debian11:nonroot

COPY build/libs/app.jar .

CMD ["app.jar"]