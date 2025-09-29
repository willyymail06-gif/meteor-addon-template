public class FaceBaritoneGoalModule extends Module {

    public FaceBaritoneGoalModule() {
        super("FaceBaritoneGoal", Category.Player);
    }

    @Override
    public void onTick() {
        // 取得 Baritone 主要實例
        Baritone baritone = BaritoneAPI.getProvider().getPrimaryBaritone();
        if (baritone == null) return;

        // 取得目標
        IGoal goal = baritone.getPathingBehavior().getGoal();
        if (goal == null) return;

        BlockPos goalPos = goal.getGoalPos();
        if (goalPos == null) return;

        // 計算方向
        double dx = goalPos.getX() + 0.5 - mc.player.getX();
        double dy = goalPos.getY() - (mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose()));
        double dz = goalPos.getZ() + 0.5 - mc.player.getZ();

        double distXZ = Math.sqrt(dx * dx + dz * dz);

        float yaw = (float)(Math.toDegrees(Math.atan2(dz, dx)) - 90.0);
        float pitch = (float)-Math.toDegrees(Math.atan2(dy, distXZ));

        // 更新玩家視角
        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);
    }
}
