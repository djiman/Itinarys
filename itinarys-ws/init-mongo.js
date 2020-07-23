db.createUser(
    {
        user: "gorgui",
        pwd: "12345",
        roles:
            [
                { role: "readWrite", db: "itinarys" }
            ]
    }
)