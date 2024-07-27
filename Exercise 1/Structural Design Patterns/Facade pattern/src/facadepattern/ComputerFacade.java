package facadepattern;

public class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade(CPU cpu, Memory memory, HardDrive hardDrive) {
        this.cpu = cpu;
        this.memory = memory;
        this.hardDrive = hardDrive;
    }

    public void startComputer() {
        cpu.freeze();
        memory.load(0L, hardDrive.read(0L, 1024));
        cpu.jump(0L);
        cpu.execute();
    }
}
