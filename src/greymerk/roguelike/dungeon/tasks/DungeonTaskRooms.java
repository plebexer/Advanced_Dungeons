package greymerk.roguelike.dungeon.tasks;

import java.util.List;
import java.util.Random;

import greymerk.roguelike.dungeon.DungeonNode;
import greymerk.roguelike.dungeon.IDungeon;
import greymerk.roguelike.dungeon.IDungeonLevel;
import greymerk.roguelike.dungeon.ILevelLayout;
import greymerk.roguelike.dungeon.base.IDungeonRoom;
import greymerk.roguelike.dungeon.settings.ISettings;
import greymerk.roguelike.worldgen.IWorldEditor;

public class DungeonTaskRooms implements IDungeonTask{

	@Override
	public boolean execute(IWorldEditor editor, Random rand, IDungeon dungeon, ISettings settings, int index) {
		
		List<IDungeonLevel> levels = dungeon.getLevels();
		
		// generate rooms
		IDungeonLevel level = levels.get(index);
                {
			ILevelLayout layout = level.getLayout();
			List<DungeonNode> nodes = layout.getNodes();
			DungeonNode startRoom = layout.getStart();
			DungeonNode endRoom = layout.getEnd();
			for (DungeonNode node : nodes){
				if(node == startRoom || node == endRoom) continue;
				IDungeonRoom toGenerate = node.getRoom();
				toGenerate.generate(editor, rand, level.getSettings(), node.getEntrances(), node.getPosition());	
			}
		}
		return index == levels.size() - 1;
	}
}
