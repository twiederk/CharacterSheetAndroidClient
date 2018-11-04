package com.android.ash.charactersheet.gui.admin.skill;

import static com.android.ash.charactersheet.Constants.INTENT_EXTRA_DATA_OBJECT;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.gui.util.AdministrationListActivity;
import com.android.ash.charactersheet.gui.widget.NameDisplayArrayAdapter;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.util.SkillComparator;

/**
 * The skill administration. Display all skills in a list. The option menu offers the options to create a skill, export,
 * import and delete all skills. The content menu of each skills offers to edit or delete the skill.
 */
public class SkillAdministrationListActivity extends AdministrationListActivity<Skill> {

    private static final int CONTEXT_MENU_SKILL_DELETE = 100;

    /**
     * Menu contains menu items to edit or delete skill.
     * 
     * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View,
     *      android.view.ContextMenu.ContextMenuInfo)
     */
    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View view, final ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        final AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;
        final Skill skill = (Skill) getListView().getAdapter().getItem(info.position);
        menu.setHeaderTitle(skill.getName());
        menu.add(0, CONTEXT_MENU_SKILL_DELETE, 0, R.string.skill_administration_list_context_menu_delete_skill);
    }

    /**
     * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onContextItemSelected(final MenuItem menuItem) {

        switch (menuItem.getItemId()) {

        case CONTEXT_MENU_SKILL_DELETE:
            final Skill skill = getSkill(menuItem);
            return deleteSkill(skill);

        default:
            return super.onContextItemSelected(menuItem);
        }
    }

    private Skill getSkill(final MenuItem menuItem) {
        final AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) menuItem.getMenuInfo();
        final Skill skill = (Skill) getListView().getAdapter().getItem(menuInfo.position);
        return skill;
    }

    private boolean deleteSkill(final Skill skill) {
        gameSystem.deleteSkill(skill);
        final ArrayAdapter<Skill> adapter = (ArrayAdapter<Skill>) getListView().getAdapter();
        adapter.remove(skill);
        getListView().clearTextFilter();
        return true;
    }

    @Override
    protected int getTitleResource() {
        return R.string.skill_administration_list_title;
    }

    @Override
    protected ArrayAdapter<Skill> getAdapter() {
        final ArrayAdapter<Skill> adapter = new NameDisplayArrayAdapter<Skill>(this, displayService,
                gameSystem.getAllSkills());
        adapter.sort(new SkillComparator());
        return adapter;
    }

    @Override
    protected Intent getCreateIntent() {
        return new Intent(this, SkillAdministrationCreateActivity.class);
    }

    @Override
    protected Intent getEditIntent(final Skill skill) {
        return new Intent(this, SkillAdministrationEditActivity.class)
                .putExtra(INTENT_EXTRA_DATA_OBJECT, skill.getId());
    }

}
