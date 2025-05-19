try {
  db = db.getSiblingDB('app_prod');

  db.createUser({
    user: 'app_user',
    pwd: 'AppS3cr3t!2024',
    roles: [
      { role: 'readWrite', db: 'app_prod' },
      { role: 'read', db: 'admin' }
    ]
  });

  // Coleções iniciais
  db.createCollection('usuarios', {
    validator: {
      $jsonSchema: {
        bsonType: "object",
        required: ["email", "dataCriacao"],
        properties: {
          email: { bsonType: "string", pattern: "^.+\@.+\..+$" },
          dataCriacao: { bsonType: "date" }
        }
      }
    }
  });

  db.createCollection('logs', {
    capped: true,
    size: 1048576, // 1MB
    max: 1000
  });

  print('Inicialização concluída com sucesso');
} catch (e) {
  print('Erro na inicialização: ' + e);
  throw e;
}