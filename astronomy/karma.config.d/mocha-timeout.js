// These tests check years of sun and moon times against published tables, and a shared CI runner can
// be several times slower than a workstation; Mocha's 2-second default fails them for that alone.
config.set({ client: { mocha: { timeout: 30000 } } });
