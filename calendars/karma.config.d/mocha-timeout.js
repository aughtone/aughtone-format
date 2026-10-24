// These tests check decades of calendar data against published tables, and a shared CI runner can
// be several times slower than a workstation; Mocha's 2-second default fails them for that alone.
config.set({ client: { mocha: { timeout: 30000 } } });
