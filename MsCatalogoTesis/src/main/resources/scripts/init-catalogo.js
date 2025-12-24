// ======================================================
//  CATÁLOGO COMPLETO (MongoDB Compass -> _MONGOSH)
//  BD: ms_catalogo_tesis
//  Colección única: catalogo
//  Incluye:
//   - creación de colección
//   - índices (único grupo+codigo)
//   - fix por si existe "active" mal escrito
//   - UPSERT de TODO el catálogo (sin duplicar)
// ======================================================

db = db.getSiblingDB("ms_catalogo_tesis");

// 1) Crear colección (si ya existe, no falla)
try {
  db.createCollection("catalogo");
} catch (e) {
  if (e.codeName !== "NamespaceExists") throw e;
}

// 2) Índices
db.catalogo.createIndex(
  { grupo: 1, codigo: 1 },
  { unique: true, name: "uq_grupo_codigo" }
);

db.catalogo.createIndex(
  { grupo: 1, activo: 1, orden: 1 },
  { name: "idx_grupo_activo_orden" }
);

db.catalogo.createIndex({ updatedAt: -1 }, { name: "idx_updatedAt_desc" });

// 3) FIX: si alguna vez se insertó "active" en vez de "activo"
db.catalogo.updateMany(
  { active: { $exists: true } },
  { $set: { activo: true, updatedAt: new Date() }, $unset: { active: "" } }
);

// 4) Datos completos (UPSERT)
const now = new Date();

const items = [
  // =========================
  // ESTADO_PROYECTO
  // =========================
  {
    grupo: "ESTADO_PROYECTO",
    codigo: "PENDIENTE",
    nombre: "PENDIENTE",
    orden: 1,
  },
  {
    grupo: "ESTADO_PROYECTO",
    codigo: "REVISION_FORMATO",
    nombre: "REVISION_FORMATO",
    orden: 2,
  },
  {
    grupo: "ESTADO_PROYECTO",
    codigo: "EN_ASESOR",
    nombre: "EN_ASESOR",
    orden: 3,
  },
  {
    grupo: "ESTADO_PROYECTO",
    codigo: "LISTO_SORTEO",
    nombre: "LISTO_SORTEO",
    orden: 4,
  },
  {
    grupo: "ESTADO_PROYECTO",
    codigo: "EN_JURADO",
    nombre: "EN_JURADO",
    orden: 5,
  },
  {
    grupo: "ESTADO_PROYECTO",
    codigo: "APROBADO",
    nombre: "APROBADO",
    orden: 6,
  },

  // =========================
  // ESTADO_BORRADOR
  // =========================
  {
    grupo: "ESTADO_BORRADOR",
    codigo: "REVISION_FORMATO",
    nombre: "REVISION_FORMATO",
    orden: 1,
  },
  {
    grupo: "ESTADO_BORRADOR",
    codigo: "EN_JURADO",
    nombre: "EN_JURADO",
    orden: 2,
  },
  {
    grupo: "ESTADO_BORRADOR",
    codigo: "DICTAMINACION",
    nombre: "DICTAMINACION",
    orden: 3,
  },
  {
    grupo: "ESTADO_BORRADOR",
    codigo: "FINALIZADO",
    nombre: "FINALIZADO",
    orden: 4,
  },

  // =========================
  // ROL_JURADO
  // =========================
  { grupo: "ROL_JURADO", codigo: "PRESIDENTE", nombre: "PRESIDENTE", orden: 1 },
  { grupo: "ROL_JURADO", codigo: "SECRETARIO", nombre: "SECRETARIO", orden: 2 },
  { grupo: "ROL_JURADO", codigo: "VOCAL", nombre: "VOCAL", orden: 3 },

  // =========================
  // DOCENTE_CATEGORIA
  // =========================
  {
    grupo: "DOCENTE_CATEGORIA",
    codigo: "NOMBRADO",
    nombre: "NOMBRADO",
    orden: 1,
  },
  {
    grupo: "DOCENTE_CATEGORIA",
    codigo: "CONTRATADO",
    nombre: "CONTRATADO",
    orden: 2,
  },

  // =========================
  // DOCENTE_GRADO
  // =========================
  { grupo: "DOCENTE_GRADO", codigo: "MAESTRO", nombre: "MAESTRO", orden: 1 },
  { grupo: "DOCENTE_GRADO", codigo: "DOCTOR", nombre: "DOCTOR", orden: 2 },

  // =========================
  // REVISION_FASE
  // =========================
  { grupo: "REVISION_FASE", codigo: "PROYECTO", nombre: "PROYECTO", orden: 1 },
  { grupo: "REVISION_FASE", codigo: "BORRADOR", nombre: "BORRADOR", orden: 2 },

  // =========================
  // DICTAMEN_ESTADO
  // =========================
  {
    grupo: "DICTAMEN_ESTADO",
    codigo: "APROBADO",
    nombre: "APROBADO",
    orden: 1,
  },
  {
    grupo: "DICTAMEN_ESTADO",
    codigo: "OBSERVADO",
    nombre: "OBSERVADO",
    orden: 2,
  },
  {
    grupo: "DICTAMEN_ESTADO",
    codigo: "RECHAZADO",
    nombre: "RECHAZADO",
    orden: 3,
  },

  // =========================
  // SUSTENTA_MODALIDAD
  // =========================
  {
    grupo: "SUSTENTA_MODALIDAD",
    codigo: "PRESENCIAL",
    nombre: "PRESENCIAL",
    orden: 1,
  },
  {
    grupo: "SUSTENTA_MODALIDAD",
    codigo: "VIRTUAL",
    nombre: "VIRTUAL",
    orden: 2,
  },
  { grupo: "SUSTENTA_MODALIDAD", codigo: "MIXTA", nombre: "MIXTA", orden: 3 },

  // =========================
  // SUSTENTA_RESULTADO
  // =========================
  {
    grupo: "SUSTENTA_RESULTADO",
    codigo: "APROBADO_UNANIMIDAD",
    nombre: "APROBADO_UNANIMIDAD",
    orden: 1,
  },
  {
    grupo: "SUSTENTA_RESULTADO",
    codigo: "APROBADO_MAYORIA",
    nombre: "APROBADO_MAYORIA",
    orden: 2,
  },
  {
    grupo: "SUSTENTA_RESULTADO",
    codigo: "DESAPROBADO",
    nombre: "DESAPROBADO",
    orden: 3,
  },

  // =========================
  // ESPECIALIDAD (según tu imagen)
  // =========================
  {
    grupo: "ESPECIALIDAD",
    codigo: "GESTION_TECNOLOGIAS_INFORMACION",
    nombre: "GESTION DE TECNOLOGIAS DE INFORMACION",
    orden: 1,
  },
  {
    grupo: "ESPECIALIDAD",
    codigo: "REDES_TELECOMUNICACIONES",
    nombre: "REDES Y TELECOMUNICACIONES",
    orden: 2,
  },
  {
    grupo: "ESPECIALIDAD",
    codigo: "INGENIERIA_SOFTWARE",
    nombre: "INGENIERIA DEL SOFTWARE",
    orden: 3,
  },
  {
    grupo: "ESPECIALIDAD",
    codigo: "CIENCIAS_COMPUTACION",
    nombre: "CIENCIAS DE LA COMPUTACION",
    orden: 4,
  },
];

// UPSERT masivo
db.catalogo.bulkWrite(
  items.map((x) => ({
    updateOne: {
      filter: { grupo: x.grupo, codigo: x.codigo },
      update: {
        $set: {
          nombre: x.nombre,
          activo: true,
          orden: x.orden,
          updatedAt: now,
        },
        $setOnInsert: {
          createdAt: now,
        },
      },
      upsert: true,
    },
  })),
  { ordered: false }
);

// 5) Verificar todo
print("\n========================================");
print("CATÁLOGO INSERTADO/ACTUALIZADO CORRECTAMENTE");
print("========================================\n");

db.catalogo
  .aggregate([
    { $group: { _id: "$grupo", count: { $sum: 1 } } },
    { $sort: { _id: 1 } },
  ])
  .forEach((g) => {
    print(`${g._id}: ${g.count} items`);
  });

print("\n");
