
user-rest-api
 -> UserControllerV1
 -> UserInputDataCreateV1

    VVVVVV  Can access classes just from the previous layer

user-service
 -> UserService

    VVVVVV  Can access classes just from the previous layer

user-model
 -> User
 -> UserRepository