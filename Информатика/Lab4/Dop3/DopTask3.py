from MainTask import deserialize

def serialize_xml(data, root_name, level=0):
    indent = "  " * level
    xml_lines = []
    
    if level == 0:
        xml_lines.append('<?xml version="1.0" encoding="UTF-8"?>')
    
    if isinstance(data, dict):
        if level == 0:
            xml_lines.append(f'<{root_name}>')
        for key, value in data.items():
            tag_name = key.replace(' ', '_')
            if isinstance(value, (dict, list)):
                xml_lines.append(f'{indent}  <{tag_name}>')
                xml_lines.append(serialize_xml(value, key, level + 1))
                xml_lines.append(f'{indent}  </{tag_name}>')
            else:
                xml_lines.append(f'{indent}  <{tag_name}>{xml_replace(value)}</{tag_name}>')
        if level == 0:
            xml_lines.append(f'</{root_name}>')
    
    elif isinstance(data, list):
        for item in data:
            if isinstance(item, (dict, list)):
                xml_lines.append(f'{indent}  <item>')
                xml_lines.append(serialize_xml(item, "item", level + 1))
                xml_lines.append(f'{indent}  </item>')
            else:
                xml_lines.append(f'{indent}  <item>{xml_replace(item)}</item>')
    
    return "\n".join(xml_lines)


def xml_replace(text):
    if not isinstance(text, str):
        text = str(text)
    
    replacements = [
        ('&', '&amp;'),
        ('<', '&lt;'),
        ('>', '&gt;'),
        ('"', '&quot;'),
        ("'", '&apos;')
    ]
    
    for old, new in replacements:
        text = text.replace(old, new)
    
    return text

with open('расписание.yaml', 'r', encoding='utf-8') as f:
    yaml_text = f.read()

result = deserialize(yaml_text)
xml_output = serialize_xml(result, "расписание")

with open('расписание.xml', 'w', encoding='utf-8') as f:
    f.write(xml_output)

print(xml_output)
